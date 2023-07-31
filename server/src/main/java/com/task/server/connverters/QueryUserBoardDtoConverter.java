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
        UUID id = (UUID) result[0];
        UUID categoryId = (UUID) result[1];
        String categoryLabel = (String) result[2];
        UUID taskId = (UUID) result[3];
        String taskDescription = (String) result[4];
        String taskHeading = (String) result[5];
        Integer noOfComment = (Integer) result[6];
        Integer noOfAssignee = (Integer) result[7];

        return new QueryUserBoardDto(id, categoryId, categoryLabel, taskId, taskDescription, taskHeading, noOfComment, noOfAssignee);
    }
}
