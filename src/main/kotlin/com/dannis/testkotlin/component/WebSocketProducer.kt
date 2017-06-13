package com.dannis.testkotlin.component

import java.text.SimpleDateFormat
import java.util.Date
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation .Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component("webSocketProducer")
class WebSocketProducer {

    @Autowired
    private val template: SimpMessagingTemplate? = null

    fun sendMessageTo(topic: String, message: String) {
        log.info("产生消息,topic:{},msg:{}", topic, message)
        val builder = StringBuilder()
        builder.append("[")
        builder.append(dateFormatter.format(Date()))
        builder.append("]")
        builder.append(message)
        this.template!!.convertAndSend("/topic/" + topic, builder.toString())
    }

    companion object {
        private val log = LoggerFactory.getLogger(WebSocketProducer::class.java)
        private val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    }
}
