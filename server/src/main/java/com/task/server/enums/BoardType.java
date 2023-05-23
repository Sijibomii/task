package com.task.server.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum BoardType {

    USERBOARD("USERBOARD"),
    TASKBOARD("TASKBOARD"),
    PROJECTBOARD("PROJECTBOARD"),
    TEAMBOARD("TEAMBOARD");

    @Setter
    private String name;


    @JsonValue
    public int getOrdinal(){
        return this.ordinal();
    }
}
