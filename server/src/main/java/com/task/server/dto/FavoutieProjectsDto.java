package com.task.server.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class FavoutieProjectsDto {
    private String label;

    private UUID id;

    private UUID board_id;
}
