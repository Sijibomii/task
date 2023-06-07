package com.task.webscokethandler.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
public class WebSocket {
    
    @Autowired
    private static SimpMessagingTemplate messagingTemplate;

    public static void broadcastMessage(String topic, Object message) {
        messagingTemplate.convertAndSend(topic, message);
    }
}
