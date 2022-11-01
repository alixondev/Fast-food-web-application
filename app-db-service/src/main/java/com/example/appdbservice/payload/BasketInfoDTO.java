package com.example.appdbservice.payload;

import com.example.appdbservice.entity.enums.BasketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketInfoDTO {

    private Long userId;

    private BasketType basketType;

    private List<BasketProductInfoDTO> basketProductInfoDTOS;

    private Double finalPrice;

}
