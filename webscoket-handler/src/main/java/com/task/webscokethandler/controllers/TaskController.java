package com.task.webscokethandler.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import com.task.webscokethandler.types.TaskMessage;

@Controller
public class TaskController {
    
    @MessageMapping("/task")
    public void handleMessage(@Payload TaskMessage payload) {
        // Handle the incoming message from the client
        System.out.println("Received message from client: " +payload.getOp());
        
        // Perform any necessary processing or logic based on the message
        
        // Send a response back to the client if needed
        // For example, you can use SimpMessagingTemplate to send a message to a specific destination
    }
}
