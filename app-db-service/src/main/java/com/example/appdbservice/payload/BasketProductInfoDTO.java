package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketProductInfoDTO {

    private Long productId;

    private Integer amount;

    private Long basketId;

}
