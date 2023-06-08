package com.task.webscokethandler.config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

// on start up web handler should announce to the server that it just came online through kafka. 
// users request a web handler ip and server returns one from it
@Component
public class StartUpConfig implements CommandLineRunner{

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Value("${webhandler.codename}")
	private String codename;

    @Value("${webhandler.id}")
    private String id;

    @Value("${server.port}")
    private String port;
    
    @Override
    public void run(String... args) throws Exception {
        // broadcast to kafka that a webhandler is online
        // if kafka not working or not reachable shut down
        Map<String,String> map = new HashMap<>();
        map.put("codename", codename);
        map.put("id", id);
        map.put("port", port);
        try {
            kafkaTemplate.send("webhandler", new ObjectMapper().writeValueAsString(map)).get(); 
            System.out.println("Kafka is available! Application started!");
        } catch (Exception e) {
            if (e.getCause() instanceof TimeoutException) {
                // Kafka is not available or not reachable
                System.err.println("Kafka is not available! Application shutting down...");
                
                System.exit(1); 
            } else {
                // Handle other exceptions
                e.printStackTrace();
            }
        }
    }

}
