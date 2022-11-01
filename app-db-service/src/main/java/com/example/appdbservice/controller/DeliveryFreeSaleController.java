package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.DeliverySaleInfoDTO;
import com.example.appdbservice.utils.AppConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AppConstant.DELIVERY_SALE_CONTROLLER)
public interface DeliveryFreeSaleController {


    @GetMapping(AppConstant.VIEW + "/{id}")
    ApiResult<DeliverySaleInfoDTO> getOne(@PathVariable Long id);

}
