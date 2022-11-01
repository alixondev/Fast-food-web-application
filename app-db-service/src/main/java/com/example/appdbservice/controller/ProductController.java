package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.ProductAddDTO;
import com.example.appdbservice.payload.ProductInfoDTO;
import com.example.appdbservice.payload.ProductUpdateDTO;
import com.example.appdbservice.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(AppConstant.PRODUCT_CONTROLLER_PATH)
public interface ProductController {

    @GetMapping(AppConstant.VIEW_ONE + "/{id}")
    ApiResult<ProductInfoDTO> getOne(@PathVariable Long id);

    @GetMapping(AppConstant.VIEW)
    ApiResult<List<ProductInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size);

    @PostMapping(AppConstant.ADD)
    ApiResult<ProductInfoDTO> add(@RequestBody ProductAddDTO productAddDTO);

    @PutMapping(AppConstant.UPDATE + "/{id}")
    ApiResult<ProductInfoDTO> update(@RequestBody ProductUpdateDTO productUpdateDTO, @PathVariable Long id);

    @DeleteMapping(AppConstant.DELETE + "/{id}")
    ApiResult<?> delete(@PathVariable Long id);

}
