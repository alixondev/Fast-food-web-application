package com.example.appdbservice.service;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.SaleAddDTO;
import com.example.appdbservice.payload.SaleInfoDTO;

import java.util.List;

public interface SaleService {
    ApiResult<List<SaleInfoDTO>> getAll(int page, int size);
    ApiResult<SaleInfoDTO> getOne(Long id);

    ApiResult<SaleInfoDTO> add(SaleAddDTO saleAddDTO);

    ApiResult<SaleInfoDTO> update(SaleAddDTO saleUpdateDTO, Long id);
    ApiResult<?> delete(Long id);
}
