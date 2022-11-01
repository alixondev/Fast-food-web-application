package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.utils.AppConstant;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AppConstant.FAVOURITE_CONTROLLER_PATH)
public interface FavouriteController {

    @PostMapping(AppConstant.ADD + "/{id}")
    ApiResult<?> add(@PathVariable Long id);

    @DeleteMapping(AppConstant.DELETE + "/{id}")
    ApiResult<?> deleteOne(@PathVariable Long id);


}
