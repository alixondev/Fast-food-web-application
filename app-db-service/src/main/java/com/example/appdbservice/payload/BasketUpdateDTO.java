package com.example.appdbservice.payload;


import com.example.appdbservice.entity.enums.BasketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketUpdateDTO {

    private Long userId;

    private BasketType basketType;

    private Double finalPrice;

}
