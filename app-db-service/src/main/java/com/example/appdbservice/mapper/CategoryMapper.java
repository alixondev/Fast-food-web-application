package com.example.appdbservice.mapper;

import com.example.appdbservice.entity.product.Category;
import com.example.appdbservice.payload.CategoryInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryInfoDTO entityToInfoDTO(Category category) {
        return new CategoryInfoDTO(
                category.getUzName(),
                category.getDescription()
        );
    }

}
