package com.dannis.testkotlin.web

import com.dannis.testkotlin.component.WebSocketProducer
import org.springframework.beans.factory.annotation.Autowired
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
class WebSocketController {

    @Autowired
    var producer: WebSocketProducer? = null

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
}