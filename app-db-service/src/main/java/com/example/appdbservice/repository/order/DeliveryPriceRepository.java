package com.example.appdbservice.repository.order;

import com.example.appdbservice.entity.order.DeliveryPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryPriceRepository extends JpaRepository<DeliveryPrice, Long> {



    Optional<DeliveryPrice> findByBranchId(Long branch_id);

    Optional<DeliveryPrice> getByBranchId(Long id);
}
