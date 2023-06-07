package com.task.webscokethandler.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import java.util.List;
import com.task.webscokethandler.types.TaskCreate;
import com.task.webscokethandler.types.TaskCreateMessage;
import com.task.webscokethandler.utils.WebSocket;
import java.util.UUID;

@Component
public class TaskConsumers {
    
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    //  a task can only be mapped to a project
    @KafkaListener(topics = "task-created",containerFactory = "kafkaListenerContainerFactory")
    public void onTaskCreated(List<ConsumerRecord<String,String>> records){
        for (int i = 0; i < records.size(); i++) {
            ConsumerRecord<String,String> record  = records.get(i);
            // remember to log output
            TaskCreate task = JSON.parseObject(record.value(), TaskCreate.class);
            if(task == null){
                return;
            }
            // send to all who should have an update
            String project_id = task.getProject_id();
            String topic ="project/"+project_id;

            TaskCreateMessage tc = new TaskCreateMessage();
            // standardize all op
            tc.setOp("task:created");
            tc.setId(UUID.randomUUID().toString());
            tc.setTask(task);

            WebSocket.broadcastMessage(topic, tc);
        }
    }
}