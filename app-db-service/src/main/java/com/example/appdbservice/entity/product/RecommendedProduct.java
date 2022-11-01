package com.example.appdbservice.entity.product;

import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "update recommended_product set deleted=true where id=?")
@Where(clause = "deleted=false")
public class RecommendedProduct extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}
