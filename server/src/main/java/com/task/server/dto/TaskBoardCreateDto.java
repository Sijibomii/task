package com.task.server.dto;

import com.task.server.entity.Projects;
import com.task.server.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskBoardCreateDto {
    private Users creator;
    private Boolean isPrivate;
    private Projects project;
}
