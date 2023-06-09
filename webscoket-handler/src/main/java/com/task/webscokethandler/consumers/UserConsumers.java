package com.task.webscokethandler.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import java.util.List;
import com.task.webscokethandler.utils.WebSocket;
import java.util.UUID;
import com.github.sijibomii.taskcore.entity.Users;

@Component
public class UserConsumers {

    @KafkaListener(topics = "auth-user",containerFactory = "kafkaListenerContainerFactory")
    public void onUserAuth(List<ConsumerRecord<String,String>> records){
        for (int i = 0; i < records.size(); i++) {
            ConsumerRecord<String,String> record  = records.get(i);
            // remember to log output
            Users user = JSON.parseObject(record.value(), Users.class);
        
            if(user == null){
                return;
            }


        }
    }

}
