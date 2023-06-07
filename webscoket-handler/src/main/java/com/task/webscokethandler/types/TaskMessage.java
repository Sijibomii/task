package com.task.webscokethandler.types;

import lombok.Data;

@Data
public class TaskMessage {
    // operation
    private String op;
    // message id
    private String id;
    // task id
    private String task_id;
    
}

