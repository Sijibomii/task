package com.task.server.dto;

import com.task.server.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectCreateDto {
    
    private String label;

    private String description;

    private Users creator;
}
