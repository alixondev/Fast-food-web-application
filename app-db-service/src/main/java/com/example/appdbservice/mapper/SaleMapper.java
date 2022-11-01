package com.example.appdbservice.mapper;


import com.example.appdbservice.entity.order.Sale;
import com.example.appdbservice.payload.SaleInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    SaleInfoDTO entityToInfoDTO(Sale sale);

    SaleInfoDTO saleResToDoDTO(Sale sale);
}
