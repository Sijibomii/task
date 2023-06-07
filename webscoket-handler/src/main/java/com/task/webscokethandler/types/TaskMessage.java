package com.task.webscokethandler.types;

import lombok.Data;

//  user create new task. server to kafka to handler to connected guys

@Data
public class TaskMessage {
    // operation
    private String op;
    // message id
    private String id;
    // task id
    private String task_id;
}

