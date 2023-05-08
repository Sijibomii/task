package com.task.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RegisterDto {

    private String displayName;

    private String password;

    private String email;

}
