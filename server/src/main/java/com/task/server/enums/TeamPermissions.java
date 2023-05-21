package com.task.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonValue;


@AllArgsConstructor
@Getter
public enum TeamPermissions {
    CAN_ADD_TASK("CAN_ADD_TASK");

    @Setter
    private String name;


    @JsonValue
    public int getOrdinal(){
        return this.ordinal();
    }
}
