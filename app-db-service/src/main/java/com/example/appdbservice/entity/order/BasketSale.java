package com.example.appdbservice.entity.order;

import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "basket_sale")
public class BasketSale extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id", insertable = false, updatable = false)
    @ToString.Exclude
    private Basket basket;

    @Column(name = "basket_id")
    private Long basketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "count_sale_id", insertable = false, updatable = false)
    @ToString.Exclude
    private CountSale countSale;

    @Column(name = "count_sale_id")
    private Long countSaleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_sale_id", insertable = false, updatable = false)
    @ToString.Exclude
    private PriceSale priceSale;

    @Column(name = "price_sale_id")
    private Long priceSaleId;

}
