package com.task.server.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import com.task.server.constants.base.BaseEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum EventEnum implements BaseEnum{
    
    USER_LOGIN("user login"),
   
    USER_REGISTER("user register");

    @Setter
    private String eventName;

    @Override
    @JsonValue
    public int getOrdinal(){
        return this.ordinal();
    }

    

}
