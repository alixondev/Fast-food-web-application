package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.CategoryAddDTO;
import com.example.appdbservice.payload.CategoryInfoDTO;
import com.example.appdbservice.payload.CategoryUpdateDTO;
import com.example.appdbservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController{

    private final CategoryService categoryService;

    @Override
    public ApiResult<List<CategoryInfoDTO>> getAll(int page, int size) {
        return categoryService.getAll(page, size);
    }

    @Override
    public ApiResult<CategoryInfoDTO> getOne(Long id) {
        return categoryService.getOne(id);
    }

    @Override
    public ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO) {
        return categoryService.add(categoryAddDTO);
    }

    @Override
    public ApiResult<CategoryInfoDTO> update(CategoryUpdateDTO categoryUpdateDTO, Long id) {
        return categoryService.update(categoryUpdateDTO,id);
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return categoryService.delete(id);
    }
}
