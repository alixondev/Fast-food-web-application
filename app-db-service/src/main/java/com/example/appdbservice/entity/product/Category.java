package com.example.appdbservice.entity.product;

import com.example.appdbservice.entity.template.AbsNameEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "category")
@Getter
@Setter
@ToString
@SQLDelete(sql = "update category set deleted = true where id = ?")
@Where(clause = "deleted=false")
public class Category extends AbsNameEntity {

    @ManyToOne
    private Category parentCategoryId;

    private String description;


    public Category(Category category, String description, String nameUz, String nameRu) {
    }
}
