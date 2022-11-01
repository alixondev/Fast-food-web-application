package com.example.appdbservice.service;

import com.example.appdbservice.entity.order.Basket;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.PriceSaleDTO;

import java.util.List;

public interface PriceSaleService {
    ApiResult<List<?>> getAll(int page, int size);

    ApiResult<?> getOne(Long id);

    ApiResult<?> add(PriceSaleDTO saleAddDTO);

    ApiResult<?> update(PriceSaleDTO saleUpdateDTO, Long id);

    ApiResult<?> delete(Long id);

    void check(Basket basket);
}
