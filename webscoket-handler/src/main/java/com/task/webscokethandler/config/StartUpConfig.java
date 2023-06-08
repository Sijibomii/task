package com.task.webscokethandler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

// on start up web handler should announce to the server that it just came online through kafka. 
// users request a web handler ip and server returns one from it
@Component
public class StartUpConfig implements CommandLineRunner{

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    
    @Override
    public void run(String... args) throws Exception {
        // broadcast to kafka that a webhandler is online
        System.out.println("Application started!#############################################");
    }

}
