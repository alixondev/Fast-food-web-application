package com.example.appdbservice.entity.order;

import com.example.appdbservice.entity.enums.OrderStatus;
import com.example.appdbservice.entity.template.AbsLongEntity;
import com.example.appdbservice.entity.users.Address;
import com.example.appdbservice.entity.users.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "orders")
public class Order extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User courier;

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    private PayType payType;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Details> detailsList;

    private Double basketPrice;

    private Double fare;

    private OrderStatus orderType;

    private String comment;

    private Double finalPrice;

}
