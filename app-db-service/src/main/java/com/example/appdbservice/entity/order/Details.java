package com.example.appdbservice.entity.order;

import com.example.appdbservice.entity.product.Product;
import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Details extends AbsLongEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    private Product product;

    private Integer amount;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Details details = (Details) o;
        return getId() != null && Objects.equals(getId(), details.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
