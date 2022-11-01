package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.BasketAddDTO;
import com.example.appdbservice.payload.BasketInfoDTO;
import com.example.appdbservice.payload.BasketUpdateDTO;
import com.example.appdbservice.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

@RequestMapping(AppConstant.BASKET_CONTROLLER_PATH)
public interface BasketController {

    @GetMapping(AppConstant.VIEW + "/{id}")
    ApiResult<BasketInfoDTO> getOne(@PathVariable Long id);

    @PostMapping(AppConstant.ADD)
    ApiResult<BasketInfoDTO> add(@RequestBody BasketAddDTO basketAddDTO);

    @PutMapping(AppConstant.UPDATE + "/{id}")
    ApiResult<BasketInfoDTO> update(@RequestBody BasketUpdateDTO basketUpdateDTO, @PathVariable Long id);

    @DeleteMapping(AppConstant.CLEAR + "/{id}")
    ApiResult<?> clear(@PathVariable Long id);

}
