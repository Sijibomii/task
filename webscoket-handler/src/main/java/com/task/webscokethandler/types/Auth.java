package com.task.webscokethandler.types;

import lombok.Data;

@Data
public class Auth {
    private String accessToken;
    private String refreshToken;
}
