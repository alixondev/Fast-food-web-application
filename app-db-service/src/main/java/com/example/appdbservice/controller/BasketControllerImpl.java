package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.BasketAddDTO;
import com.example.appdbservice.payload.BasketInfoDTO;
import com.example.appdbservice.payload.BasketUpdateDTO;
import com.example.appdbservice.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BasketControllerImpl implements BasketController {

    private final BasketService basketService;

    @Override
    public ApiResult<BasketInfoDTO> getOne(Long id) {
        return basketService.getOne(id);
    }

    @Override
    public ApiResult<BasketInfoDTO> add(BasketAddDTO basketAddDTO) {
        return basketService.add(basketAddDTO);
    }

    @Override
    public ApiResult<BasketInfoDTO> update(BasketUpdateDTO basketUpdateDTO, Long id) {
        return basketService.update(basketUpdateDTO, id);
    }
    @Override
    public ApiResult<?> clear(Long id) {
        return basketService.clear(id);
    }
}
