package com.task.server.connverters;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import com.task.server.dto.QueryUserBoardDto;

@Component
public class QueryUserBoardDtoConverter {
    public List<QueryUserBoardDto> convertToDtoList(List<Object[]> resultList) {
        return resultList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private QueryUserBoardDto convertToDto(Object[] result) {

        UUID id = (result[0] != null) ? (UUID) result[0] : null;
        UUID categoryId = (result[1] != null) ? (UUID) result[1] : null;
        String categoryLabel = (result[2] != null) ? (String) result[2] : ""; 
        UUID taskId = (result[3] != null) ? (UUID) result[3] : null;
        String taskDescription = (result[4] != null) ? (String) result[4] : ""; 
        String taskHeading = (result[5] != null) ? (String) result[5] : ""; 
        Integer noOfComment = (result[6] != null) ? (Integer) result[6] : 0; 
        Integer noOfAssignee = (result[7] != null) ? (Integer) result[7] : 0;

        return new QueryUserBoardDto(id, categoryId, categoryLabel, taskId, taskDescription, taskHeading, noOfComment, noOfAssignee);
    }
}
