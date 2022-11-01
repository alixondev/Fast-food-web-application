package com.example.appdbservice.entity.order;

import com.example.appdbservice.entity.product.Product;
import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "count_sale")
@SQLDelete(sql = "update count_sale set deleted = true where id = ?")
@Where(clause = "deleted=false")
public class CountSale extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Product getProduct;           // qaysi product dan

    private Integer getProductAmount;     // qancha olsa

    @ManyToOne(fetch = FetchType.LAZY)
    private Product giftProduct;      // nima qo'shib beramiz

    private Integer giftProductAmount;   // nechta qo'shib beramiz

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id",insertable = false,updatable = false)
    private Sale sale;

    @Column(name = "sale_id")
    private Long saleId;

    public CountSale(Product getProduct, Integer getProductAmount, Product giftProduct, Integer giftProductAmount, Sale sale) {
        this.getProduct = getProduct;
        this.getProductAmount = getProductAmount;
        this.giftProduct = giftProduct;
        this.giftProductAmount = giftProductAmount;
        this.sale = sale;
    }
}
