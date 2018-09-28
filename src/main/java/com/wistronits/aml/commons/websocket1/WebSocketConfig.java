//package com.wistronits.aml.commons.websocket1;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//    @Autowired
//    private ChatHandler chatHandler;
//    @Autowired
//    private WebSocketHandshakeInterceptor webSocketHandshakeInterceptor;
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//    registry.addHandler(chatHandler,"/chat/chatEndpointWisely").setAllowedOrigins("*")
//    .addInterceptors(webSocketHandshakeInterceptor).withSockJS();
//    }
//     @Bean
//    public ServletServerContainerFactoryBean createWebSocketContainer() {
//        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
//        container.setMaxTextMessageBufferSize(8192*4);
//        container.setMaxBinaryMessageBufferSize(8192*4);
//        return container;
//    }
//
//}
