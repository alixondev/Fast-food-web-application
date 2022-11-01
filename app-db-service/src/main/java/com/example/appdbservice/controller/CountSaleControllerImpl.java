package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.CountSaleDTO;
import com.example.appdbservice.service.CountSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CountSaleControllerImpl implements CountSaleController {

    private final CountSaleService countSaleService;

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return countSaleService.getAll(page, size);
    }

    @Override
    public ApiResult<?> getOne(Long id) {
        return countSaleService.getOne(id);
    }

    @Override
    public ApiResult<?> add(CountSaleDTO saleAddDTO) {
        return countSaleService.add(saleAddDTO);
    }

    @Override
    public ApiResult<?> update(CountSaleDTO saleUpdateDTO, Long id) {
        return countSaleService.update(saleUpdateDTO, id);
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return delete(id);
    }
}
