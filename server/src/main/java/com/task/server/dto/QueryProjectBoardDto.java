package com.task.server.dto;

import java.util.UUID;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryProjectBoardDto {

    private UUID board_id;

    private List<TasksDto> tasks;

    private List<CategoryDto> categories;
}
