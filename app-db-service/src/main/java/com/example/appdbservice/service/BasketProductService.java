package com.example.appdbservice.service;

import com.example.appdbservice.entity.order.BasketProduct;
import com.example.appdbservice.payload.BasketProductInfoDTO;

import java.util.Optional;

public interface BasketProductService {

    BasketProductInfoDTO entityToInfoDTO(BasketProduct basketProduct);

    Optional<BasketProduct> findByProductIdAndBasketId(Long productId, Long basketId);

    void save(BasketProduct giftBasketProduct);
}
