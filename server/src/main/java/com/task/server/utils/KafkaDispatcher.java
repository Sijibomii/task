package com.task.server.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import com.task.server.constants.EventEnum;
import com.alibaba.fastjson.JSON;
import com.task.server.entity.Events;
import com.task.server.services.EventService;

//  will be called when messages need to be dispatched
public class KafkaDispatcher {

    @Autowired
    private static KafkaTemplate<String, String> kafkaTemplate;
 
    @Autowired
    private static EventService eventService;


    @Async
    public static void disptach(String topic, Object data, String description, EventEnum e){
        // send event
        kafkaTemplate.send(topic, JSON.toJSONString(data));

        // create event obj and store in db
        Events event = new Events();
        event.setCreatedOn(new Date());
        event.setDescription(description);
        event.setEvent(e);
        eventService.save(event);
    }
}
