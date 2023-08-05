package com.task.server.connverters;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import com.task.server.dto.QueryProjectBoardDto;
import com.task.server.dto.TasksDto;
import com.task.server.dto.CategoryDto;

@Component
public class QueryProjectBoardDtoConverter {
    
    @Autowired
    private TasksDtoConverter taskscoConverter;

    @Autowired
    private CategoriesDtoConverter catconverter;

    public QueryProjectBoardDto convertToDtoList(List<Object[]> resultList) {

        Object[] obj = resultList.get(0);
        UUID board_id = (UUID) obj[0];

        List<Object[]> taskList = resultList.stream().map((Object[] o) -> {
            return Arrays.copyOfRange(o, 1, o.length);
        }).collect(Collectors.toList());

        List<Object[]> catList = resultList.stream().map((Object[] o) -> {
            return Arrays.copyOfRange(o, 0, 3);
        }).collect(Collectors.toList());

        Set<TasksDto> tasks = taskscoConverter.convertToDtoList(taskList);

        Set<CategoryDto> cats = catconverter.convertToDtoList(catList);

        return new QueryProjectBoardDto(board_id, tasks, cats);
    }

   
}
