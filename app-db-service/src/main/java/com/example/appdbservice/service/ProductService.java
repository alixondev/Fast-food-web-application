package com.example.appdbservice.service;

import com.example.appdbservice.entity.product.Product;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.ProductAddDTO;
import com.example.appdbservice.payload.ProductInfoDTO;
import com.example.appdbservice.payload.ProductUpdateDTO;

import java.util.List;

public interface ProductService {

    ApiResult<List<ProductInfoDTO>> getAll(int page, int size);

    ApiResult<ProductInfoDTO> getOne(Long id);

    ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO);

    ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Long id);

    ApiResult<?> delete(Long id);

    Product getByIdOrElseThrow(Long id);

}
