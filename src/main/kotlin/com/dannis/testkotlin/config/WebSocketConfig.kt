package com.dannis.testkotlin.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry

// 参考: https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/websocket.html
/*
 * STOMP: Simple (or Streaming) Text Orientated Messaging Protocol.
 * Created by sam on 17-6-13.
 * */
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : AbstractWebSocketMessageBrokerConfigurer() {

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/stomp").withSockJS()
    }

    override fun configureMessageBroker(config: MessageBrokerRegistry?) {
//        config!!.enableSimpleBroker("/topic", "/queue")
        config!!.enableSimpleBroker("/topic")

        config.setApplicationDestinationPrefixes("/app")
        /* The "/app" prefix is arbitrary. You can pick any prefix. It’s simply meant to differentiate messages
        to be routed to message-handling methods to do application work vs messages to be routed to
        the broker to broadcast to subscribed clients. */
    }
}