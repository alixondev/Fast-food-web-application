package com.example.appdbservice.service;


import com.example.appdbservice.entity.product.Category;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.CategoryAddDTO;
import com.example.appdbservice.payload.CategoryInfoDTO;
import com.example.appdbservice.payload.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {

    ApiResult<List<CategoryInfoDTO>> getAll(int page, int size);

    ApiResult<CategoryInfoDTO> getOne(Long id);

    ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO);

    ApiResult<CategoryInfoDTO> update(CategoryUpdateDTO categoryUpdateDTO, Long id);

    ApiResult<?> delete(Long id);

    Category getByIdOrElseThrow(Long id);


}
