package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryAddDTO {

    private Long parentId;

    @NotNull(message = "Name must not be null!")
    private String nameUz;

    @NotNull(message = "Name must not be null!")
    private String nameRu;

    private String description;

}
