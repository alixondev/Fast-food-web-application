package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductInfoDTO {

    private String nameUZ;

    private String nameRu;

    private String description;

    private Long attachmentId;

    private Long categoryId;


}
