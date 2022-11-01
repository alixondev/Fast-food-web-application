package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeliveryPriceAddDTO {

     private Double minPrice;

     private Double minKm;

     private Double priceEveryKm;

     private Long branchId;

}
