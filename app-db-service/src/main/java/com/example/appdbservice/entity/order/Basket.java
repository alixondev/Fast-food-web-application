package com.example.appdbservice.entity.order;

import com.example.appdbservice.entity.enums.BasketType;
import com.example.appdbservice.entity.template.AbsLongEntity;
import com.example.appdbservice.entity.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "basket")
public class Basket extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(fetch = FetchType.LAZY)
    private List<BasketProduct> basketProductList;

    private BasketType basketType;

    private Double price;

    private Double finalPrice;

}
