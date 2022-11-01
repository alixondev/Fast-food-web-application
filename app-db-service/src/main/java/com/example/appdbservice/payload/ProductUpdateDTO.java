package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductUpdateDTO {

    private String nameUZ;

    private String nameRu;

    private String description;

    private Long categoryId;

}
