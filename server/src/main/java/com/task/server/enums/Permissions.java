package com.task.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonValue;


@AllArgsConstructor
@Getter
public enum Permissions {
    // org permissions 1-200
    CAN_ADD_CHANNEL_ORGANIZATION("channel:create:organization:", 1, "can create a new channel mapped to an organization"),
    CAN_ADD_USER_TO_ORG("user:add:organization", 2, "can add a new user into an organization"),

    // team permissions 201-400
    CAN_ADD_CHANNEL_TEAM("channel:create:team:", 201, "can create a new channel mapped to a team"),


    // project permissions 401-600
    CAN_ADD_CHANNEL_PROJECT("channel:create:project:", 401, "can create a new channel mapped to a project");

    // task permissions 601-800

    
    @Setter
    private String name;
    private int code;
    private String description;


    @JsonValue
    public int getOrdinal(){
        return this.ordinal();
    }
}
