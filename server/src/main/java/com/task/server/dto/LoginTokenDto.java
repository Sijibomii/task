package com.task.server.dto;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginTokenDto {
    private UUID id;
    private String email;
    private String accessToken;
    private String refreshToken;
}
