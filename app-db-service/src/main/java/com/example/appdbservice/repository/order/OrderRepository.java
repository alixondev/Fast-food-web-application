package com.example.appdbservice.repository.order;

import com.example.appdbservice.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {

}
