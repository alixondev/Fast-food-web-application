package com.example.appdbservice.service;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.DeliveryPriceAddDTO;
import com.example.appdbservice.payload.DeliveryPriceInfoDTO;
import com.example.appdbservice.payload.DeliveryPriceUpdateDTO;

import java.util.List;

public interface DeliveryPriceService {

    ApiResult<List<DeliveryPriceInfoDTO>> getAll(int page, int size);

    ApiResult<DeliveryPriceInfoDTO> getOne(Long id);

    ApiResult<DeliveryPriceInfoDTO> add(DeliveryPriceAddDTO deliveryPriceAddDTO);

    ApiResult<DeliveryPriceInfoDTO> update(DeliveryPriceUpdateDTO deliveryPriceUpdateDTO, Long id);

    ApiResult<?> delete(Long id);

}
