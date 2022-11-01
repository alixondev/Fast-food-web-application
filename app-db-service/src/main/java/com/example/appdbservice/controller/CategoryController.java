package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.CategoryAddDTO;
import com.example.appdbservice.payload.CategoryInfoDTO;
import com.example.appdbservice.payload.CategoryUpdateDTO;
import com.example.appdbservice.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(AppConstant.CATEGORY_CONTROLLER_PATH)
public interface CategoryController {


    @GetMapping(AppConstant.VIEW)
    ApiResult<List<CategoryInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size);

    @RequestMapping(AppConstant.VIEW_ONE + "/{id}")
    ApiResult<CategoryInfoDTO> getOne(@PathVariable Long id);

    @RequestMapping(AppConstant.ADD)
    ApiResult<CategoryInfoDTO> add(@RequestBody CategoryAddDTO categoryAddDTO);

    @RequestMapping(AppConstant.UPDATE)
    ApiResult<CategoryInfoDTO> update(@RequestBody CategoryUpdateDTO categoryUpdateDTO,
                                      @PathVariable Long id);

    @RequestMapping(AppConstant.DELETE + "/{id}")
    ApiResult<?> delete(@PathVariable Long id);

}
