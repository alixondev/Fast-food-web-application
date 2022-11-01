package com.example.appdbservice.entity.product;

import com.example.appdbservice.entity.template.AbsNameEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "products")
@SQLDelete(sql = "update products set deleted = true, active = false where id = ?")
@Where(clause = "deleted=false")
public class Product extends AbsNameEntity {

    @Column(columnDefinition = "text")
    private String description;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @ToString.Exclude
    private Attachment attachment;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Category category;

    public Product(String uzName, String ruName, String description, Attachment attachment, Double price, Category category) {
        super(uzName, ruName);
        this.description = description;
        this.attachment = attachment;
        this.price = price;
        this.category = category;
    }

    //  private Sale sale; //

}
