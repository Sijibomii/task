package com.task.webscokethandler.config;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;



@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

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

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
                    String destination = accessor.getDestination();
                    if (destination != null && destination.startsWith("/org/")) {
                        String orgId = destination.substring(5); // Extract organization ID from the destination
                        // Perform any necessary logic based on the organization ID
                        // You can modify the destination or perform other actions accordingly
                        // For example, you can route the message to a specific handler based on the organization ID
                        accessor.setDestination("/topic/org/" + orgId);
                    }
                }
                return message;
            }
        });
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
                System.out.println("new handshake done");
            }
        };
    }

    private DefaultHandshakeHandler tokenHandshakeHandler() {
        return new DefaultHandshakeHandler();
    }

    private boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            // If the token has expired, it is considered invalid
            if (claims.getExpiration().before(new Date())) {
                return false;
            }
            return true; 
        } catch (Exception e) {
            // Token verification failed
            return false;
        }
    }
}
