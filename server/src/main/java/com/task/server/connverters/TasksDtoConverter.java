package com.task.server.connverters;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

import com.task.server.dto.TasksDto;


@Component
public class TasksDtoConverter {

    public List<TasksDto> convertToDtoList(List<Object[]> resultList) {
        return resultList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private TasksDto convertToDto(Object[] result) {

        UUID categoryId = (result[0] != null) ? (UUID) result[0] : null;
        UUID taskId = (result[2] != null) ? (UUID) result[2] : null;
        String taskDescription = (result[3] != null) ? (String) result[3] : ""; 
        String taskHeading = (result[4] != null) ? (String) result[4] : ""; 
        Integer noOfComment = (result[5] != null) ? (Integer) result[5] : 0; 
        Integer noOfAssignee = (result[6] != null) ? (Integer) result[6] : 0;

        return new TasksDto(categoryId, taskId, taskDescription, taskHeading, noOfComment, noOfAssignee);
    }
}
