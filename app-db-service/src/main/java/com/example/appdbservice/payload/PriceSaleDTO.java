package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PriceSaleDTO {
    private Long id;
    private Long saleId;
    private Double price;
    private String discountType;
    private Double amount;
}
