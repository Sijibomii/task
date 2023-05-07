package com.task.server.dto;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginInputDto {

    private String email;
    private String password;
    private String captacha;
}
