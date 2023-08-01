package com.task.server.dto;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TasKUserDto {
    private UUID id;
    
    private String avartar;

    private String name;
}
