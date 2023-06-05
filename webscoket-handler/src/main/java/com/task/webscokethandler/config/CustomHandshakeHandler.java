package com.task.webscokethandler.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

// we're overriding the handleInvalidUpgradeHeader method, which is called before the handshake is performed. 
// Inside this method, we extract the token from the request URI and perform any necessary validation or processing. 
// Then, we add the token to the WebSocket session attributes. 
// After that, we call the super.handleInvalidUpgradeHeader method to proceed with the handshake process.

// Please note that the handleInvalidUpgradeHeader method is called when the upgrade header is invalid, so it provides 
// an opportunity to intercept the request and add custom attributes before the handshake.
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected void handleInvalidUpgradeHeader(ServerHttpRequest request, ServerHttpResponse response) {
        // Extract the token from the request URI
        UriComponents uriComponents = UriComponentsBuilder.fromUri(request.getURI()).build();
        String token = uriComponents.getQueryParams().getFirst("token");

        // You can perform any token validation or processing here

        // Add the token to the WebSocket session attributes
        Map<String, Object> attributes = getAttributes(request);
        attributes.put("token", token);

        super.handleInvalidUpgradeHeader(request, response);
    }
}

