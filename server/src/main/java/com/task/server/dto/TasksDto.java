package com.task.server.dto;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TasksDto {

    private UUID category_id;

    private UUID id;

    private String description;

    private String heading;

    private int comment;

    private int no_of_assignee;

    private List<TagDto> tags;

    private List<TasKUserDto> assignees;
    
}
