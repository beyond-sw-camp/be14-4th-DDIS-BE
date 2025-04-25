package com.DDIS.chatRoom.Command.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 클라이언트가 구독할 수 있는 주소(prefix)
        config.enableSimpleBroker("/topic"); // 구독 경로

        // 클라이언트가 메시지를 보낼 때 사용하는 prefix
        config.setApplicationDestinationPrefixes("/app"); // 발신 경로
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 연결할 WebSocket 엔드포인트
        registry.addEndpoint("/ws-chat").setAllowedOriginPatterns("*").withSockJS();
    }
}
