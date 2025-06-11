package com.example.hello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.example.hello.security.JwtHandshakeInterceptor;
import com.example.hello.security.JwtUserHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private JwtHandshakeInterceptor jwtHandshakeInterceptor;

    @Autowired
    private JwtUserHandshakeHandler jwtUserHandshakeHandler;

    // Constructor injection thay vì @Autowired
    public WebSocketConfig(JwtHandshakeInterceptor jwtHandshakeInterceptor, 
                          JwtUserHandshakeHandler jwtUserHandshakeHandler) {
        this.jwtHandshakeInterceptor = jwtHandshakeInterceptor;
        this.jwtUserHandshakeHandler = jwtUserHandshakeHandler;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
         System.out.println("⚡ registerStompEndpoints được gọi!");
        registry
            .addEndpoint("/ws-chat")
            .setHandshakeHandler(jwtUserHandshakeHandler)
            .addInterceptors(jwtHandshakeInterceptor)
            .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/queue", "/topic");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }
}


