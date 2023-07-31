package com.task.server.connverters;

import com.task.server.dto.CategoryDto;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class CategoriesDtoConverter {
   
    public List<CategoryDto> convertToDtoList(List<Object[]> resultList) {
        return resultList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CategoryDto convertToDto(Object[] result) {

        UUID categoryId = (result[0] != null) ? (UUID) result[0] : null;
        String categoryLabel = (result[2] != null) ? (String) result[2] : ""; 

        return new CategoryDto(categoryId, categoryLabel);
    }
}
