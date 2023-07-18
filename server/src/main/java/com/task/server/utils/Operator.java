package com.task.server.utils;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum Operator {
    USER_REQUEST_PASSWORD_RESET_EMAIL("user-request-password-reset-email"),
    USER_PASSWORD_RESET_CODE_VERIFIED("user-password-reset-code-verified"),
    USER_REQUEST_LOGIN_EMAIL("user-request-login-email"),
    USER_REQUEST_REGISTER_EMAIL("user-request-register-email"),
    USER_REGISTER("user-register"),
    USER_LOGIN("user-login");


    @Setter
    private String operator;


    @JsonValue
    public int getOrdinal(){
        return this.ordinal();
    }
}
