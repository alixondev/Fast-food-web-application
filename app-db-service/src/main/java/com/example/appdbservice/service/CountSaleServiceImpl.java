package com.example.appdbservice.service;

import com.example.appdbservice.entity.order.BasketProduct;
import com.example.appdbservice.entity.order.CountSale;
import com.example.appdbservice.entity.order.Sale;
import com.example.appdbservice.entity.product.Product;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.CountSaleDTO;
import com.example.appdbservice.repository.order.CountSaleRepository;
import com.example.appdbservice.repository.order.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CountSaleServiceImpl implements CountSaleService {

    private final CountSaleRepository countSaleRepository;
    private final ProductServiceImpl productService;
    private final SaleServiceImpl saleService;
    private final BasketProductService basketProductService;
    private final SaleRepository saleRepository;

    @Override
    public ApiResult<?> getAll(int page, int size) {

        List<CountSale> saleList = countSaleRepository.findAll();
        List<CountSaleDTO> saleInfoDTOList = new ArrayList<>();
        for (CountSale sale : saleList) {
            saleInfoDTOList.add(entityToInfoDTO(sale));
        }
        return ApiResult.successResponse(saleInfoDTOList);
    }

    @Override
    public ApiResult<?> getOne(Long id) {
        CountSale sale = getByIdOrElseThrow(id);
        if (Objects.isNull(sale)) {
            throw RestException.notFound("Count sale");
        }
        return ApiResult.successResponse(entityToInfoDTO(sale));
    }

    @Override
    public ApiResult<?> add(CountSaleDTO saleAddDTO) {
        CountSale countSale = dTOToEntity(saleAddDTO);
        CountSale save = countSaleRepository.save(countSale);
        CountSaleDTO priceSaleDTO = entityToInfoDTO(save);
        return ApiResult.successResponse(priceSaleDTO);

    }

    @Override
    public ApiResult<?> update(CountSaleDTO saleUpdateDTO, Long id) {
        CountSale countSale = countSaleRepository.findById(id).orElseThrow(() -> new RestException("Sale", HttpStatus.NOT_FOUND));

        updateEntity(saleUpdateDTO, countSale);
        CountSale save = countSaleRepository.save(countSale);

        return ApiResult.successResponse(entityToInfoDTO(save));
    }

    @Override
    public void check(BasketProduct basketProduct) {

        Date date = new Date(System.currentTimeMillis());
        Date datePlusOneDay = new Date(System.currentTimeMillis() + 86400000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Optional<Sale> saleOptional = saleRepository.getSaleByCurrentTime(
                date,
                timestamp,
                date,
                datePlusOneDay
        );

        if (saleOptional.isEmpty())
            return;

          Optional<CountSale> countSaleOptional = countSaleRepository.getBySaleIdAndGetProductIdAndGetProductAmountLessThanEqual(
                  saleOptional.get().getId(),
                  basketProduct.getProduct().getId(),
                  basketProduct.getAmount()
          );

          if (countSaleOptional.isEmpty())
              return;

          CountSale countSale = countSaleOptional.get();
          Optional<BasketProduct> basketProductOptional = basketProductService.findByProductIdAndBasketId(
                  basketProduct.getProduct().getId(),
                  basketProduct.getBasket().getId()
          );
          BasketProduct giftBasketProduct;
          if (basketProductOptional.isEmpty()) {
              giftBasketProduct = new BasketProduct(
                      countSale.getGiftProduct(),
                      countSale.getGiftProductAmount(),
                      0d,
                      basketProduct.getBasket()
              );
          }

          giftBasketProduct = basketProductOptional.get();
          giftBasketProduct.setAmount(giftBasketProduct.getAmount() + countSale.getGiftProductAmount());
          giftBasketProduct.setPrice(0d);
          basketProductService.save(giftBasketProduct);
    }


    @Override
    public ApiResult<?> delete(Long id) {
        CountSale sale = getByIdOrElseThrow(id);
        countSaleRepository.delete(sale);
        return ApiResult.successResponse("Success");
    }

    private CountSaleDTO entityToInfoDTO(CountSale sale) {

        return new CountSaleDTO(
                sale.getId(),
                sale.getSale().getId(),
                sale.getGetProduct().getId(),
                sale.getGetProductAmount(),
                sale.getGiftProduct().getId(),
                sale.getGiftProductAmount());
    }

    public CountSale getByIdOrElseThrow(Long id) {
        return countSaleRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Count Sale")
        );
    }

    private void updateEntity(CountSaleDTO dto, CountSale countSale) {

        Sale sale = saleService.getByIdOrElseThrow(dto.getSaleId());
        Product product = productService.getByIdOrElseThrow(dto.getProductId());
        Product gift = productService.getByIdOrElseThrow(dto.getGiftProductId());
        countSale.setSale(sale);

        countSale.setGetProduct(product);
        countSale.setGiftProduct(gift);
        countSale.setGetProductAmount(dto.getAmount());
        countSale.setGiftProductAmount(dto.getGiftAmount());
    }

    private CountSale dTOToEntity(CountSaleDTO dto) {
        Product product = productService.getByIdOrElseThrow(dto.getProductId());
        Product gift = productService.getByIdOrElseThrow(dto.getGiftProductId());
        Sale sale = saleService.getByIdOrElseThrow(dto.getSaleId());
        return new CountSale(product, dto.getAmount(), gift, dto.getGiftAmount(),sale);

    }

}
