package com.task.server.dto;

import com.task.server.entity.Projects;
import com.task.server.entity.Users;
import com.task.server.enums.BoardType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskBoardCreateDto {
    private Users creator;
    private Boolean isPrivate;
    private Projects project;
    private BoardType boardType;
}
