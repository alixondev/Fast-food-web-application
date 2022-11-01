package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.PriceSaleDTO;
import com.example.appdbservice.service.PriceSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceSaleControllerImpl implements PriceSaleController {
    private final PriceSaleService priceSaleService;

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return priceSaleService.getAll(page, size);
    }

    @Override
    public ApiResult<?> getOne(Long id) {
        return priceSaleService.getOne(id);
    }

    @Override
    public ApiResult<?> add(PriceSaleDTO saleAddDTO) {
        return priceSaleService.add(saleAddDTO);
    }

    @Override
    public ApiResult<?> update(PriceSaleDTO saleUpdateDTO, Long id) {
        return priceSaleService.update(saleUpdateDTO, id);
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return priceSaleService.delete(id);
    }
}
