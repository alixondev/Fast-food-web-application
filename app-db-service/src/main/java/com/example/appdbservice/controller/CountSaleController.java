package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.CountSaleDTO;
import com.example.appdbservice.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

@RequestMapping(AppConstant.COUNT_SALE_CONTROLLER_PATH)
public interface CountSaleController {

    @GetMapping(AppConstant.VIEW)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);


    @GetMapping(AppConstant.VIEW + "/{id}")
    ApiResult<?> getOne(@PathVariable Long id);

    @PostMapping(AppConstant.ADD)
    ApiResult<?> add(@RequestBody CountSaleDTO saleAddDTO);

    @RequestMapping(AppConstant.UPDATE + "/{id}")
    ApiResult<?> update(@RequestBody CountSaleDTO saleUpdateDTO,@PathVariable Long id);

    @RequestMapping(AppConstant.DELETE + "/{id}")
    ApiResult<?> delete(@PathVariable Long id);
}
