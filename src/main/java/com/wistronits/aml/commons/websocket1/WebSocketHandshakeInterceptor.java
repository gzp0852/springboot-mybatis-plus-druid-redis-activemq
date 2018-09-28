//package com.wistronits.aml.commons.websocket1;
//
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import java.util.Map;
//
//@Component
//public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
//
//    @Override public boolean beforeHandshake(ServerHttpRequest serverHttpRequest,
//            ServerHttpResponse serverHttpResponse,
//            org.springframework.web.socket.WebSocketHandler webSocketHandler, Map<String, Object> map)
//            throws Exception {
//        if (serverHttpRequest instanceof ServletServerHttpRequest) {
//            map.put("username","sdadsdadsa");
//        }
//        return true;
//    }
//
//    @Override public void afterHandshake(ServerHttpRequest serverHttpRequest,
//            ServerHttpResponse serverHttpResponse,
//            org.springframework.web.socket.WebSocketHandler webSocketHandler, Exception e) {
//
//    }
//}
