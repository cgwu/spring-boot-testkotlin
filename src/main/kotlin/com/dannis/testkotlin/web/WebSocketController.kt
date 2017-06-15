package com.dannis.testkotlin.web

import com.dannis.testkotlin.component.WebSocketProducer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.*
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpServletRequest


/**
 * Created by sam on 17-6-13.
 *   @Autowired
wsProducer: WebSocketProducer
 */

@Controller
@RequestMapping("/websocket")
@MessageMapping("ws")
class WebSocketController {
    companion object {
        private val log = LoggerFactory.getLogger(WebSocketController::class.java)
    }

    @Autowired
    private val template: SimpMessagingTemplate? = null

    @Autowired
    var producer: WebSocketProducer? = null

    /* HTTP请求方法 */
    @RequestMapping("/msg-list/{chanel}")
    fun msgList(@PathVariable chanel: String, request: HttpServletRequest): String {
        request.setAttribute("userChanel", chanel)
        return "websocket/msg-list"
    }

    @RequestMapping("/send/{topic}")
    @ResponseBody
    fun send(@PathVariable topic: String, @RequestParam message: String): String {
        producer!!.sendMessageTo(topic, message)
        return "OK-Sent"
    }

    /* WebSocket请求方法 */
    @MessageMapping("bar.{baz}")
    fun handleBaz(@DestinationVariable baz: String) {
        log.info("服务器接收到WebSocket消息: $baz")
    }

    @MessageMapping("logbody")
    fun logMsg(@Payload body: String, @Header("priority") priority: String, @Headers headers: Map<String, Any>) {
        log.info("服务器接收到WebSocket消息体内容: $body")
        log.info("优先级: $priority")
        log.info("所有头部: $headers")
        producer!!.sendMessageTo("user1", body)
    }

    // 测试发现: @DestinationVariable 和 @Header,@Payload 不能同时使用,否则前者取值错误.
    @MessageMapping("user")
    fun sendtouser(@Header("userId") userId: String, @Payload body: String) {
        log.info("发送消息到用户: $userId, Msg: $body")
        // 此类消息客户端应使用的订阅路径为: /user/{userId}/msg
        template!!.convertAndSendToUser(userId, "/msg", body)
    }

    @MessageMapping("topic")
    fun broadcast(@Payload body: String) {
        log.info("广播消息: $body")
        template!!.convertAndSend("/topic", body)
    }

}