package com.task.server.connverters;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import com.task.server.dto.QueryProjectBoardDto;

@Component
public class QueryProjectBoardDtoConverter {
    public List<QueryProjectBoardDto> convertToDtoList(List<Object[]> resultList) {
        return resultList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private QueryProjectBoardDto convertToDto(Object[] result) {

        UUID board_id = (result[0] != null) ? (UUID) result[0] : null;
        UUID categoryId = (result[1] != null) ? (UUID) result[1] : null;
        String categoryLabel = (result[2] != null) ? (String) result[2] : ""; 
        UUID taskId = (result[3] != null) ? (UUID) result[3] : null;
        String taskDescription = (result[4] != null) ? (String) result[4] : ""; 
        String taskHeading = (result[5] != null) ? (String) result[5] : ""; 
        Integer noOfComment = (result[6] != null) ? (Integer) result[6] : 0; 
        Integer noOfAssignee = (result[7] != null) ? (Integer) result[7] : 0;

        return new QueryProjectBoardDto(board_id, categoryId, categoryLabel, taskId, taskDescription, taskHeading, noOfComment, noOfAssignee);
    }
}
