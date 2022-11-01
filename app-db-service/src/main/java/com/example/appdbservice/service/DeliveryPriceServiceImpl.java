package com.example.appdbservice.service;

import com.example.appdbservice.entity.order.DeliveryPrice;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.DeliveryPriceAddDTO;
import com.example.appdbservice.payload.DeliveryPriceInfoDTO;
import com.example.appdbservice.payload.DeliveryPriceUpdateDTO;
import com.example.appdbservice.repository.order.DeliveryPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryPriceServiceImpl implements DeliveryPriceService{

    private final DeliveryPriceRepository deliveryPriceRepository;
    private final BranchService branchService;

    @Override
    public ApiResult<List<DeliveryPriceInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<DeliveryPrice> deliveryPricePage = deliveryPriceRepository.findAll(pageable);
        List<DeliveryPrice> deliveryPrices = deliveryPricePage.getContent();
        List<DeliveryPriceInfoDTO> deliveryPriceInfoDTOS = deliveryPrices
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(deliveryPriceInfoDTOS);
    }

    @Override
    public ApiResult<DeliveryPriceInfoDTO> getOne(Long id) {
        DeliveryPrice deliveryPrice = getByIdOrElseThrow(id);
        return ApiResult.successResponse(entityToInfoDTO(deliveryPrice));
    }

    @Override
    public ApiResult<DeliveryPriceInfoDTO> add(DeliveryPriceAddDTO deliveryPriceAddDTO) {
        check(deliveryPriceAddDTO.getBranchId());

        DeliveryPrice deliveryPrice = new DeliveryPrice(
                deliveryPriceAddDTO.getMinPrice(),
                deliveryPriceAddDTO.getMinKm(),
                deliveryPriceAddDTO.getPriceEveryKm(),
                branchService.getByIdOrElseThrow(deliveryPriceAddDTO.getBranchId())
        );
        deliveryPriceRepository.save(deliveryPrice);
        return ApiResult.successResponse(entityToInfoDTO(deliveryPrice));
    }

    @Override
    public ApiResult<DeliveryPriceInfoDTO> update(DeliveryPriceUpdateDTO deliveryPriceUpdateDTO, Long id) {
        DeliveryPrice deliveryPrice = getByIdOrElseThrow(id);
        deliveryPrice.setMinPrice(deliveryPrice.getMinPrice());
        deliveryPrice.setPriceEveryKm(deliveryPrice.getPriceEveryKm());
        deliveryPrice.setMinKM(deliveryPriceUpdateDTO.getMinKm());
        deliveryPriceRepository.save(deliveryPrice);
        return ApiResult.successResponse(entityToInfoDTO(deliveryPrice));
    }

    @Override
    public ApiResult<?> delete(Long id) {
        DeliveryPrice deliveryPrice = getByIdOrElseThrow(id);
        deliveryPriceRepository.delete(deliveryPrice);
        return ApiResult.successResponse("Success");
    }

    private void check(Long id) {
        Optional<DeliveryPrice> deliveryPriceOptional = deliveryPriceRepository.getByBranchId(id);
        if (deliveryPriceOptional.isPresent()) throw RestException.alreadyExist("Delivery price for this branch");
    }

    private DeliveryPrice getByIdOrElseThrow(Long id) {
        return deliveryPriceRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Delivery-price")
        );
    }

    private DeliveryPriceInfoDTO entityToInfoDTO(DeliveryPrice deliveryPrice) {
        return new DeliveryPriceInfoDTO(
                deliveryPrice.getMinPrice(),
                deliveryPrice.getMinKM(),
                deliveryPrice.getPriceEveryKm(),
                deliveryPrice.getBranch().getId()
        );
    }

}
