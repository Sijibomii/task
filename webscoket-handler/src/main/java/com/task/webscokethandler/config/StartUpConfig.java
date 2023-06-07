package com.task.webscokethandler.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// on start up web handler should announce to the server that it just came online through kafka. 
// users request a web handler ip and server returns one from it
@Component
public class StartUpConfig implements CommandLineRunner{
    
    @Override
    public void run(String... args) throws Exception {
        // Code to be executed when the application starts up
        System.out.println("Application started!#############################################");
    }

}
