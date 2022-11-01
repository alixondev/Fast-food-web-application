package com.example.appdbservice.repository.order;

import com.example.appdbservice.entity.enums.BasketType;
import com.example.appdbservice.entity.order.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    boolean existsByUserIdAndBasketTypeEquals(Long userId, BasketType basketType);

}
