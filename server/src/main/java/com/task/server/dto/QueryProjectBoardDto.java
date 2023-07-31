package com.task.server.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryProjectBoardDto {

    private UUID board_id;

    private UUID category_id;

    private String category_label;

    private UUID task_id;

    private String task_description;

    private String task_heading;

    private int no_of_comment;

    private int no_of_assignee;
}
