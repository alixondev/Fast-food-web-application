package com.example.appdbservice.service;

import com.example.appdbservice.entity.order.BasketProduct;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.CountSaleDTO;

public interface CountSaleService {

     ApiResult<?> getAll(int page, int size);

     ApiResult<?> getOne(Long id);

     ApiResult<?> add(CountSaleDTO saleAddDTO);

     ApiResult<?> update(CountSaleDTO saleUpdateDTO, Long id);

     void check(BasketProduct basketProduct);

     ApiResult<?> delete(Long id);
}
