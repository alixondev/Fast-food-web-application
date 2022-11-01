package com.example.appdbservice.repository.order;

import com.example.appdbservice.entity.order.DeliveryFreeSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryFreeBonusRepository extends JpaRepository<DeliveryFreeSale, Long> {

}
