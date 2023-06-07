package com.task.webscokethandler.types;

import com.github.sijibomii.taskcore.entity.Tasks;
import lombok.Data;

//  user create new task. server to kafka to handler to connected guys

@Data
public class TaskCreate{
    private String project_id;
    private Tasks task;
}