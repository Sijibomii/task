package com.task.server.consumers;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleConsumer {
    
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @KafkaListener(topics = "websocket",containerFactory = "kafkaListenerContainerFactory")
    public void onOrderSubmitted(List<ConsumerRecord<String,String>> records){}
}
