package com.task.webscokethandler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import com.alibaba.fastjson.JSON;
import com.task.webscokethandler.types.AuthMessage;

@Controller
public class UserController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    
    @MessageMapping("/topic/user/auth")
    public void handleAuthUser(@Payload AuthMessage payload) {
        // send tokens to kafka so server can verfify
        kafkaTemplate.send("auth-user", "", JSON.toJSONString(payload.getData()));
    }
}
