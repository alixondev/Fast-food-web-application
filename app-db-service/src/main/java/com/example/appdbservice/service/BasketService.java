package com.example.appdbservice.service;

import com.example.appdbservice.entity.order.Basket;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.BasketAddDTO;
import com.example.appdbservice.payload.BasketInfoDTO;
import com.example.appdbservice.payload.BasketUpdateDTO;

public interface BasketService {

    ApiResult<BasketInfoDTO> getOne(Long id);

    ApiResult<BasketInfoDTO> add(BasketAddDTO basketAddDTO);

    ApiResult<BasketInfoDTO> update(BasketUpdateDTO basketUpdateDTO, Long id);

    ApiResult<?> clear(Long id);

    Basket getByIdOrElseThrow(Long id);

    void save(Basket basket);
}
