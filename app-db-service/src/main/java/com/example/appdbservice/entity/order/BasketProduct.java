package com.example.appdbservice.entity.order;

import com.example.appdbservice.entity.product.Product;
import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity(name = "basket_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SQLDelete(sql = "update basket_product set deleted = true where id = ?")
@Where(clause = "deleted=false")
public class BasketProduct extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Integer amount;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Basket basket;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BasketProduct basketProduct = (BasketProduct) o;
        return getId() != null && Objects.equals(getId(), basketProduct.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
