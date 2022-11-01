package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountSaleDTO {
    private Long id;
    private Long saleId;
    private Long productId;
    private Integer amount;
    private Long giftProductId;
    private Integer giftAmount;
}
