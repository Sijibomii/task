package com.task.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonValue;


@AllArgsConstructor
@Getter
public enum Permissions {
    // org permissions
    CAN_ADD_TASK("CAN_ADD_TASK");


    // team permissions

    // project permissions
    
    @Setter
    private String name;


    @JsonValue
    public int getOrdinal(){
        return this.ordinal();
    }
}
