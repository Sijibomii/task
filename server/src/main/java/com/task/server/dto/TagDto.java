package com.task.server.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagDto {
    private UUID id;
    private String label;
}
