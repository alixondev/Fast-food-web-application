package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryUpdateDTO {

    private Long parentId;
    private String nameUz;
    private String nameRu;
    private String description;

}
