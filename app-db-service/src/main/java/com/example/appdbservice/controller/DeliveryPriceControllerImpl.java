package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.DeliveryPriceAddDTO;
import com.example.appdbservice.payload.DeliveryPriceInfoDTO;
import com.example.appdbservice.payload.DeliveryPriceUpdateDTO;
import com.example.appdbservice.service.DeliveryPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeliveryPriceControllerImpl implements DeliveryPriceController{

    private final DeliveryPriceService deliveryPriceService;

    @Override
    public ApiResult<List<DeliveryPriceInfoDTO>> getAll(int page, int size) {
        return deliveryPriceService.getAll(page,size);
    }

    @Override
    public ApiResult<DeliveryPriceInfoDTO> getOne(Long id) {
        return deliveryPriceService.getOne(id);
    }

    @Override
    public ApiResult<DeliveryPriceInfoDTO> add(DeliveryPriceAddDTO deliveryPriceAddDTO) {
        return deliveryPriceService.add(deliveryPriceAddDTO);
    }

    @Override
    public ApiResult<DeliveryPriceInfoDTO> update(DeliveryPriceUpdateDTO deliveryPriceUpdateDTO, Long id) {
        return deliveryPriceService.update(deliveryPriceUpdateDTO,id);
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return deliveryPriceService.delete(id);
    }
}
