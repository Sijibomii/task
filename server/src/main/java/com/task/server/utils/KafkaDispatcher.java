package com.task.server.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;

import com.alibaba.fastjson.JSON;

//  will be called when messages need to be dispatched
public class KafkaDispatcher {

    @Autowired
    private static KafkaTemplate<String, String> kafkaTemplate;
 
    
    @Async
    public static void disptach(String topic, Object data, String description){
        // send event
        kafkaTemplate.send(topic, JSON.toJSONString(data));

        // create event obj and store in db

    }
}
