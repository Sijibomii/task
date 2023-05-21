package com.task.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonValue;


@AllArgsConstructor
@Getter
public enum TaskStatus {
    OPEN("OPEN");

    @Setter
    private String name;


    @JsonValue
    public int getOrdinal(){
        return this.ordinal();
    }
}
