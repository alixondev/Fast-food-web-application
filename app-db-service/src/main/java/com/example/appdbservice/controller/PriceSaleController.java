package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.PriceSaleDTO;
import com.example.appdbservice.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

@RequestMapping(AppConstant.PRICE_SALE_CONTROLLER_PATH)
public interface PriceSaleController {


    @GetMapping(AppConstant.VIEW)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);


    @GetMapping(AppConstant.VIEW + "/{id}")
    ApiResult<?> getOne(@PathVariable Long id);

    @PostMapping(AppConstant.ADD)
    ApiResult<?> add(@RequestBody PriceSaleDTO saleAddDTO);

    @RequestMapping(AppConstant.UPDATE)
    ApiResult<?> update(@RequestBody PriceSaleDTO saleUpdateDTO, @PathVariable Long id);

    @RequestMapping(AppConstant.DELETE + "/{id}")
    ApiResult<?> delete(@PathVariable Long id);
}
