package com.task.server.connverters;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

import com.task.server.dto.TagDto;
import com.task.server.dto.TasksDto;



@Component
public class TasksDtoConverter {

    private HashMap<UUID, List<TagDto>> tk = new HashMap<>();

    public List<TasksDto> convertToDtoList(List<Object[]> resultList) {

        // sort tags according to task first
        for (int i=0; i<resultList.size(); i++) {
            Object[] arr = resultList.get(i);
            UUID task_id = (arr[arr.length-1] != null) ? (UUID) arr[arr.length-1] : null;
            if (this.tk.containsKey(task_id)) {
                // -1 -3
                UUID tagId = (arr[arr.length-2] != null) ? (UUID) arr[arr.length-2] : null;
                String tagLabel = (arr[arr.length-4] != null) ? (String) arr[arr.length-4] : ""; 
                this.tk.get(task_id).add(new TagDto(tagId, tagLabel));
            }else{
                this.tk.put(task_id, new ArrayList<TagDto>());
                UUID tagId = (arr[arr.length-2] != null) ? (UUID) arr[arr.length-2] : null;
                String tagLabel = (arr[arr.length-4] != null) ? (String) arr[arr.length-4] : ""; 
                this.tk.get(task_id).add(new TagDto(tagId, tagLabel));
            }
        }

        return resultList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private TasksDto convertToDto(Object[] result) {

        UUID categoryId = (result[0] != null) ? (UUID) result[0] : null;
        UUID taskId = (result[2] != null) ? (UUID) result[2] : null;
        String taskDescription = (result[3] != null) ? (String) result[3] : ""; 
        String taskHeading = (result[4] != null) ? (String) result[4] : ""; 
        Integer noOfComment = (result[5] != null) ? (Integer) result[5] : 0; 
        Integer noOfAssignee = (result[6] != null) ? (Integer) result[6] : 0;

        return new TasksDto(categoryId, taskId, taskDescription, taskHeading, noOfComment, noOfAssignee, this.tk.get(taskId));
    }
}

