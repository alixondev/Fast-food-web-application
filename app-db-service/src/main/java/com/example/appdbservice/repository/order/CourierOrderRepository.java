package com.example.appdbservice.repository.order;

import com.example.appdbservice.entity.order.CourierOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierOrderRepository extends JpaRepository<CourierOrder, Long> {

}
