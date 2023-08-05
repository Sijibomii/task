package com.task.server.connverters;

import com.task.server.dto.CategoryDto;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class CategoriesDtoConverter {
   
    public Set<CategoryDto> convertToDtoList(List<Object[]> resultList) {
        return resultList.stream().map(this::convertToDto).collect(Collectors.toSet());
    }

    private CategoryDto convertToDto(Object[] result) {
    
        UUID categoryId = (result[1] != null) ? (UUID) result[1] : null;
        String categoryLabel = (result[2] != null) ? (String) result[2] : ""; 

        return new CategoryDto(categoryId, categoryLabel);
    }
}
