// package com.example.hello.security;

// import jakarta.servlet.http.HttpServletRequest;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.server.ServerHttpRequest;
// import org.springframework.http.server.ServerHttpResponse;
// import org.springframework.http.server.ServletServerHttpRequest;
// import org.springframework.stereotype.Component;
// import org.springframework.web.socket.WebSocketHandler;
// import org.springframework.web.socket.server.HandshakeInterceptor;

// import java.util.Map;

// @Component
// public class JwtHandshakeInterceptor implements HandshakeInterceptor {

// @Autowired
// private JwtUtil jwtUtil;

// @Override
// public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse
// response,
// WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception
// {

// if (request instanceof ServletServerHttpRequest servletRequest) {
// HttpServletRequest httpReq = servletRequest.getServletRequest();
// String token = httpReq.getParameter("token"); // hoặc lấy từ header
// System.out.println("==================Authorization Header: " + token);
// if (token != null && token.startsWith("Bearer ")) {
// token = token.substring(7);
// String username = jwtUtil.extractUsername(token);
// if (username != null) {
// attributes.put("username", username);
// }
// }
// }
// return true;
// }

// @Override
// public void afterHandshake(ServerHttpRequest request, ServerHttpResponse
// response,
// WebSocketHandler wsHandler, Exception exception) {
// }
// }
