package com.task.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonValue;


@AllArgsConstructor
@Getter
public enum Permissions {
    // org permissions
    CAN_ADD_CHANNEL_ORGANIZATION("channel:create:organization:"),


    // team permissions
    CAN_ADD_CHANNEL_TEAM("channel:create:team:"),
    // project permissions
    CAN_ADD_CHANNEL_PROJECT("channel:create:project:");
    @Setter
    private String name;


    @JsonValue
    public int getOrdinal(){
        return this.ordinal();
    }
}
