package com.example.appdbservice.service;

import com.example.appdbservice.entity.enums.BasketType;
import com.example.appdbservice.entity.order.Basket;
import com.example.appdbservice.entity.order.BasketProduct;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.payload.*;
import com.example.appdbservice.repository.order.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService{

    private final BasketRepository basketRepository;

    private final BasketProductService basketProductService;

    public BasketServiceImpl(@Lazy BasketRepository basketRepository,
                             @Lazy BasketProductService basketProductService) {
        this.basketRepository = basketRepository;
        this.basketProductService = basketProductService;
    }

    @Override
    public ApiResult<BasketInfoDTO> getOne(Long id) {
        Basket basket = getByIdOrElseThrow(id);
        return ApiResult.successResponse(entityToInfoDTO(basket));
    }

    @Override
    public ApiResult<BasketInfoDTO> add(BasketAddDTO basketAddDTO) {
        check(basketAddDTO.getUserId());
        Basket basket = new Basket(
        );
        return null;
    }

    @Override
    public ApiResult<BasketInfoDTO> update(BasketUpdateDTO basketUpdateDTO, Long id) {
        return null;
    }

    @Override
    public ApiResult<?> clear(Long id) {
        return null;
    }

    private void check(Long userId) {
        boolean exists = basketRepository.existsByUserIdAndBasketTypeEquals(userId, BasketType.DRAFT);
        if (exists) throw RestException.alreadyExist("Basket");
    }

    public Basket getByIdOrElseThrow(Long id) {
        return basketRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Basket")
        );
    }

    @Override
    public void save(Basket basket) {
        basketRepository.save(basket);
    }

    private BasketInfoDTO entityToInfoDTO(Basket basket) {
        return new BasketInfoDTO(
                basket.getUser().getId(),
                basket.getBasketType(),
                basketProductInfoDTOS(basket.getBasketProductList()),
                basket.getFinalPrice()
        );
    }

    private List<BasketProductInfoDTO> basketProductInfoDTOS(List<BasketProduct> basketProducts) {
        return basketProducts
                .stream()
                .map(basketProductService::entityToInfoDTO)
                .collect(Collectors.toList());
    }

}
