package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.ProductAddDTO;
import com.example.appdbservice.payload.ProductInfoDTO;
import com.example.appdbservice.payload.ProductUpdateDTO;
import com.example.appdbservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public ApiResult<ProductInfoDTO> getOne(Long id) {
        return productService.getOne(id);
    }

    @Override
    public ApiResult<List<ProductInfoDTO>> getAll(int page, int size) {
        return productService.getAll(page, size);
    }

    @Override
    public ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO) {
        return productService.add(productAddDTO);
    }

    @Override
    public ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Long id) {
        return productService.update(productUpdateDTO, id);
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return productService.delete(id);
    }
}
