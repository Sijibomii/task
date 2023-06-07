package com.task.webscokethandler.types;

import lombok.Data;

//  user create new task. server to kafka to handler to connected guys

@Data
public class TaskCreateMessage {
    // operation
    private String op;
    // message id
    private String id;
   
    private TaskCreate task;
}

