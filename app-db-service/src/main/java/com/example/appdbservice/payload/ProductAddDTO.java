package com.example.appdbservice.payload;

import com.example.appdbservice.entity.product.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductAddDTO {

    @NotNull(message = "Product name must not contain null value!")
    private String nameUZ;

    @NotNull(message = "Product name must not contain null value!")
    private String nameRu;

    private String description;

    @NotNull(message = "Please select photo for your product!")
    private Attachment attachmentId;

    @NotNull(message = "Please enter category!")
    private Long categoryId;

    @NotNull(message = "Is price free? It must not be null!")
    private Double price;

}
