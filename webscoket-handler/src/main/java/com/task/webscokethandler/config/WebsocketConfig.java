package com.task.webscokethandler.config;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic","/user");
        config.setUserDestinationPrefix("/user/");
        config.setApplicationDestinationPrefixes("/app");
    } 

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/web-handler")
                .setAllowedOrigins("*")
                .addInterceptors(tokenHandshakeInterceptor())
                .setHandshakeHandler(tokenHandshakeHandler())
                .withSockJS();
    }

    private HandshakeInterceptor tokenHandshakeInterceptor() {
        return new HandshakeInterceptor() {
            
            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                String token = request.getURI().getQuery(); // Get the token from the query parameter
                // Perform token verification logic here
                boolean isValidToken = validateToken(token);
                if (isValidToken) {
                    // Add the validated token to the attributes
                    attributes.put("token", token);
                    return true; // Accept the connection
                } else {
                    // Reject the connection
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return false;
                }
            }
    
            @Override
            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
                // Empty implementation, no action needed
            }
        };
    }

    private DefaultHandshakeHandler tokenHandshakeHandler() {
        return new DefaultHandshakeHandler();
    }

    private boolean validateToken(String token) {
        // Implement your token validation logic here
        // Return true if the token is valid, false otherwise
        // You can use any token verification mechanism of your choice
        // such as decoding and validating a JWT token
        return true; // Placeholder, replace with your logic
    }
}
