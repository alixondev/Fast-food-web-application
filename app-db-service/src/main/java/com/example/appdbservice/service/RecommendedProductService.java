package com.example.appdbservice.service;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.RecommendedProductAddDTO;
import com.example.appdbservice.payload.RecommendedProductInfoDTO;

import java.util.List;

public interface RecommendedProductService {

    ApiResult<RecommendedProductInfoDTO> getOne(Long id);

    ApiResult<List<RecommendedProductInfoDTO>> getByCategoryId(Long id);

    ApiResult<List<RecommendedProductInfoDTO>> getAll(int page,int size);

    ApiResult<RecommendedProductInfoDTO> add(RecommendedProductAddDTO recommendedProductAddDTO);

    ApiResult<?> delete(Long id);

}
