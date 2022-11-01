package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketProductUpdateDTO {

    private Long productId;

    private Integer amount;

    private Long basketId;

}
