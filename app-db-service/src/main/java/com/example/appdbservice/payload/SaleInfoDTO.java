package com.example.appdbservice.payload;

import com.example.appdbservice.entity.enums.SaleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleInfoDTO {

    private Long id;

    private String name;

    private SaleTypeEnum type;

    private Boolean active;

    private Double amount;
}
