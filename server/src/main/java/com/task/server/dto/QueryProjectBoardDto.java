package com.task.server.dto;

import java.util.UUID;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryProjectBoardDto {

    private UUID board_id;

    private Set<TasksDto> tasks;

    private Set<CategoryDto> categories;
}
