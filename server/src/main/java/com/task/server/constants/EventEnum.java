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

    USER_REQUEST_LOGIN_EMAIL("user login email sent"),
   
    USER_REGISTER("user register"),

    USER_REQUEST_REGISTER_EMAIL("user register email sent"),

    USER_REQUEST_PASSWORD_RESET_EMAIL("user reset password"),

    USER_PASSWORD_RESET_CODE_VERIFIED("user reset password code verified"),

    USER_PASSWORD_RESET("user reset password");
    


    @Setter
    private String eventName;

    @Override
    @JsonValue
    public int getOrdinal(){
        return this.ordinal();
    }



}
