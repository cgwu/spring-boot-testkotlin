package com.dannis.testkotlin.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.util.AntPathMatcher
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry

/*
 * 官网参考: https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/websocket.html
 * 示例参考: http://blog.csdn.net/haoyuyang/article/details/53364372
 *
 * STOMP: Simple (or Streaming) Text Orientated Messaging Protocol.
 * Created by sam on 17-6-13.
 * */
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : AbstractWebSocketMessageBrokerConfigurer() {

    // 端点的作用——客户端在订阅或发送消息到目的地址前，要连接该端点。
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/stomp").withSockJS()
//        registry.addEndpoint("/hello").setAllowedOrigins("*").withSockJS();      // e.g.
    }

    override fun configureMessageBroker(config: MessageBrokerRegistry?) {
        //应用程序以/app为前缀，代理目的地以/topic、/user为前缀
        config!!.enableSimpleBroker("/topic", "/user")
//        config!!.enableSimpleBroker("/topic", "/queue")
//        config!!.enableSimpleBroker("/topic")

        /* The "/app" prefix is arbitrary. You can pick any prefix. It’s simply meant to differentiate messages
        to be routed to message-handling methods to do application work vs messages to be routed to
        the broker to broadcast to subscribed clients. */
        config.setApplicationDestinationPrefixes("/app")

        /*
            Using Dot as Separator in @MessageMapping Destinations
            Applications
            can also switch to using "." (dot) instead of "/" (slash) as the separator in @MessageMapping mappings
            by configuring a custom AntPathMatcher.
            e.g.:
            @Controller
            @MessageMapping("foo")
            public class FooController {
                @MessageMapping("bar.{baz}")
                public void handleBaz(@DestinationVariable String baz) { }
            }
            If the application prefix is set to "/app" then the foo method is effectively mapped to "/app/foo.bar.{baz}".
        */
        config.setPathMatcher(AntPathMatcher("."))

        // 指定用户发送一对一的主题前缀是"/user"
//        config.setUserDestinationPrefix("/user")
    }

}
