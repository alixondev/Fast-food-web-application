package com.example.appdbservice.repository.order;

import com.example.appdbservice.entity.order.CountSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountSaleRepository extends JpaRepository<CountSale, Long> {

    Optional<CountSale> findByGetProductIdAndGetProductAmountLessThanEqual(Long getProduct_id, Integer getProductAmount);

    Optional<CountSale> getBySaleIdAndGetProductIdAndGetProductAmountLessThanEqual(Long sale_id, Long getProduct_id, Integer getProductAmount);
}
