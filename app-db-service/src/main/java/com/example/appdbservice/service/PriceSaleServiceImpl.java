package com.example.appdbservice.service;

import com.example.appdbservice.entity.enums.DiscountTypeEnum;
import com.example.appdbservice.entity.order.Basket;
import com.example.appdbservice.entity.order.PriceSale;
import com.example.appdbservice.entity.order.Sale;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.PriceSaleDTO;
import com.example.appdbservice.repository.order.PriceSaleRepository;
import com.example.appdbservice.repository.order.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;


@Service
@RequiredArgsConstructor
public class PriceSaleServiceImpl implements PriceSaleService {

    private final PriceSaleRepository priceSaleRepository;
    private final SaleServiceImpl saleService;
    private final BasketService basketService;
    private final SaleRepository saleRepository;


    @Override
    public ApiResult<List<?>> getAll(int page, int size) {
        List<PriceSale> saleList = priceSaleRepository.findAll();
        List<PriceSaleDTO> saleInfoDTOList = new ArrayList<>();
        for (PriceSale sale : saleList) {
            saleInfoDTOList.add(
                    new PriceSaleDTO(
                            sale.getId(),
                            sale.getSale().getId(),
                            sale.getPrice(),
                            sale.getDiscountTypeEnum().name(),
                            sale.getSaleAmount()));
        }
        return ApiResult.successResponse(saleInfoDTOList);

    }

    @Override
    public ApiResult<?> getOne(Long id) {
        PriceSale sale = getByIdOrElseThrow(id);
        if (Objects.isNull(sale)) {
            throw RestException.notFound("Price sale");
        }
        return ApiResult.successResponse(entityToInfoDTO(sale));
    }

    @Override
    public ApiResult<?> add(PriceSaleDTO saleAddDTO) {
        PriceSale priceSale = dTOToEntity(saleAddDTO);
        PriceSale save = priceSaleRepository.save(priceSale);
        PriceSaleDTO priceSaleDTO = entityToInfoDTO(save);
        return ApiResult.successResponse(priceSaleDTO);
    }


    @Override
    public ApiResult<?> update(PriceSaleDTO saleUpdateDTO, Long id) {
        PriceSale priceSale = priceSaleRepository.findById(id).orElseThrow(() -> new RestException("Sale", HttpStatus.NOT_FOUND));
        updateEntity(saleUpdateDTO, priceSale);
        PriceSale save = priceSaleRepository.save(priceSale);
        return ApiResult.successResponse(entityToInfoDTO(save));
    }


    @Override
    public ApiResult<?> delete(Long id) {
        PriceSale sale = getByIdOrElseThrow(id);
        priceSaleRepository.delete(sale);
        return ApiResult.successResponse("Success");
    }

    @Override
    public void check(Basket basket) {
        Date date = new Date(System.currentTimeMillis());
        Date datePlusOne = new Date(System.currentTimeMillis() + 86400000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Optional<Sale> sale = saleRepository.getSaleByCurrentTime(date,timestamp,date,datePlusOne);
        if (sale.isEmpty())
            return;
        Optional<PriceSale> priceSaleOptional = priceSaleRepository.getBySaleIdAndPriceLessThanEqual(
                sale.get().getId(),
                basket.getPrice()
        );
        if (priceSaleOptional.isEmpty()) {
            return;
        } else {
            PriceSale priceSale = priceSaleOptional.get();
            if (priceSale.getDiscountTypeEnum().equals(DiscountTypeEnum.PRICE)) {
                Double finalPrice = basket.getPrice() - priceSale.getSaleAmount();
                basket.setFinalPrice(finalPrice);
            } else if (priceSale.getDiscountTypeEnum().equals(DiscountTypeEnum.PERCENT)) {
                Double finalPrice = basket.getPrice() - (basket.getPrice() * priceSale.getSaleAmount());
                basket.setFinalPrice(finalPrice);
            }
        }
        basketService.save(basket);
    }

    private PriceSaleDTO entityToInfoDTO(PriceSale sale) {
        return new PriceSaleDTO(
                sale.getId(),
                sale.getSale().getId(),
                sale.getPrice(),
                sale.getDiscountTypeEnum().name(),
                sale.getSaleAmount());
    }

    private PriceSale dTOToEntity(PriceSaleDTO dto) {
        Sale sale = saleService.getByIdOrElseThrow(dto.getSaleId());
        DiscountTypeEnum discountTypeEnum;
        if (dto.getDiscountType().equals(DiscountTypeEnum.PRICE.name())) {
            discountTypeEnum = DiscountTypeEnum.PRICE;
        } else {
            discountTypeEnum = DiscountTypeEnum.PERCENT;
        }
        return new PriceSale(sale, dto.getPrice(), discountTypeEnum, dto.getAmount());
    }

    public PriceSale getByIdOrElseThrow(Long id) {
        return priceSaleRepository.findById(id).orElseThrow(()
                -> RestException.notFound("Price sale"));
    }

    private void updateEntity(PriceSaleDTO dto, PriceSale priceSale) {
        DiscountTypeEnum discountTypeEnum;
        if (dto.getDiscountType().equals(DiscountTypeEnum.PRICE.name())) {
            discountTypeEnum = DiscountTypeEnum.PRICE;
        } else {
            discountTypeEnum = DiscountTypeEnum.PERCENT;
        }
        Sale sale = saleService.getByIdOrElseThrow(dto.getSaleId());
        priceSale.setDiscountTypeEnum(discountTypeEnum);
        priceSale.setSale(sale);
        priceSale.setPrice(dto.getPrice());
        priceSale.setSaleAmount(dto.getAmount());


    }
}

