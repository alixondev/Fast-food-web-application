package com.example.appdbservice.service;

import com.example.appdbservice.entity.order.Basket;
import com.example.appdbservice.entity.order.BasketProduct;
import com.example.appdbservice.entity.product.Product;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.BasketProductAddDTO;
import com.example.appdbservice.payload.BasketProductInfoDTO;
import com.example.appdbservice.repository.order.BasketProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class BasketProductServiceImpl implements BasketProductService {

    private final BasketProductRepository basketProductRepository;
    private final ProductService productService;
    private final BasketService basketService;
    private final CountSaleService countSaleService;
    private final PriceSaleService priceSaleService;

    public BasketProductServiceImpl(@Lazy BasketProductRepository basketProductRepository,
                                    @Lazy ProductService productService,
                                    @Lazy BasketService basketService,
                                    @Lazy CountSaleService countSaleService,
                                    @Lazy PriceSaleService priceSaleService) {
        this.basketProductRepository = basketProductRepository;
        this.productService = productService;
        this.basketService = basketService;
        this.countSaleService = countSaleService;
        this.priceSaleService = priceSaleService;
    }

    @Override
    public BasketProductInfoDTO entityToInfoDTO(BasketProduct basketProduct) {
        return new BasketProductInfoDTO(
                basketProduct.getProduct().getId(),
                basketProduct.getAmount(),
                basketProduct.getBasket().getId()
        );
    }

    @Override
    public Optional<BasketProduct> findByProductIdAndBasketId(Long productId, Long basketId) {
        return basketProductRepository.findByBasketIdAndProductId(basketId, productId);
    }

    @Override
    public void save(BasketProduct giftBasketProduct) {
        basketProductRepository.save(giftBasketProduct);
    }

    public ApiResult<BasketProductInfoDTO> getOne(Long id) {
        BasketProduct basketProduct = getByIdOrElseThrow(id);
        return ApiResult.successResponse(entityToInfoDTO(basketProduct));
    }

    public ApiResult<BasketProductInfoDTO> add(BasketProductAddDTO basketProductAddDTO) {
        Optional<BasketProduct> basketProductOptional = basketProductRepository.findByBasketIdAndProductId(
                basketProductAddDTO.getBasketId(),
                basketProductAddDTO.getProductId()
        );
        Basket basket = basketService.getByIdOrElseThrow(basketProductAddDTO.getBasketId());
        Product product = productService.getByIdOrElseThrow(basketProductAddDTO.getProductId());
        BasketProduct basketProduct;
        if (basketProductOptional.isEmpty()) {
            basketProduct = new BasketProduct(
                    product,
                    basketProductAddDTO.getAmount(),
                    product.getPrice() * basketProductAddDTO.getAmount(),
                    basket
            );
            basketProductRepository.save(basketProduct);
            Double price = Objects.isNull(basket.getPrice()) ? 0d : basket.getPrice();
            basket.setPrice(price + (product.getPrice() * basketProduct.getAmount()));
        } else {

            basketProduct = basketProductOptional.get();
            basketProduct.setAmount(basketProductAddDTO.getAmount());
            basketProduct.setPrice(product.getPrice() * basketProductAddDTO.getAmount());
            basketProductRepository.save(basketProduct);

            Double price = Objects.isNull(basket.getPrice()) ? 0d : basket.getPrice();
            Double addPrice = product.getPrice() * (basketProduct.getAmount() - basketProductAddDTO.getAmount());
            basket.setPrice(price + addPrice);
        }
        basketService.save(basket);
        checkCondition(basketProduct, basket);
        return ApiResult.successResponse(entityToInfoDTO(basketProduct));
    }

    private void checkCondition(BasketProduct basketProduct, Basket basket) {
        countSaleService.check(basketProduct);
        priceSaleService.check(basket);
    }

    public BasketProduct getByIdOrElseThrow(Long id) {
        return basketProductRepository.findById(id).orElseThrow(
                () -> RestException.notFound("BasketProduct")
        );
    }

}
