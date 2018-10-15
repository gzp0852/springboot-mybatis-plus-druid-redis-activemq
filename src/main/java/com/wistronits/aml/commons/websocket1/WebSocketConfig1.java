//package com.wistronits.aml.commons.websocket1;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//
///**
// * @author 10376 2018/3/19
// */
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig1 extends AbstractWebSocketMessageBrokerConfigurer {
//
//	/**
//	 * 设置端点、跨域
//	 *
//	 * @param stompEndpointRegistry StompEndpointRegistry
//	 */
//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
//		// 设置端点,客户端也要连接对应的端点就能连接websocket,设置跨域
//		stompEndpointRegistry.addEndpoint("/chat/chatEndpointWisely1").setAllowedOrigins("*").withSockJS();
//	}
//
//	/**
//	 * 广播式应配置一个/topic 消息代理
//	 *
//	 * @param registry MessageBrokerRegistry
//	 */
//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry registry) {// 配置消息代理(message broker)
//		// 广播式应配置一个/topic 消息代理
//		registry.enableSimpleBroker("/chatTopic1");
//	}
//}
