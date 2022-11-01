package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.DeliveryPriceAddDTO;
import com.example.appdbservice.payload.DeliveryPriceInfoDTO;
import com.example.appdbservice.payload.DeliveryPriceUpdateDTO;
import com.example.appdbservice.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(AppConstant.DELIVERY_PRICE_CONTROLLER_PATH)
public interface DeliveryPriceController {


    @GetMapping(AppConstant.VIEW)
    ApiResult<List<DeliveryPriceInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size);

    @GetMapping(AppConstant.VIEW + "/{id}")
    ApiResult<DeliveryPriceInfoDTO> getOne(@PathVariable Long id);

    @PostMapping(AppConstant.ADD)
    ApiResult<DeliveryPriceInfoDTO> add(@RequestBody DeliveryPriceAddDTO deliveryPriceAddDTO);

    @PutMapping(AppConstant.UPDATE + "/{id}")
    ApiResult<DeliveryPriceInfoDTO> update(@RequestBody DeliveryPriceUpdateDTO deliveryPriceUpdateDTO,
                                           @PathVariable Long id);

    @DeleteMapping(AppConstant.DELETE + "/{id}")
    ApiResult<?> delete(@PathVariable Long id);

}
