package com.task.webscokethandler.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter

public enum TaskTypes{
    // operation
    USERTASK("USERBOARD"),
    TASKBOARD("TASKBOARD"),
    PROJECTBOARD("PROJECTBOARD"),
    TEAMBOARD("TEAMBOARD");

    @Setter
    private String name;
}