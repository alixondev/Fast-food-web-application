package com.example.appdbservice.repository.order;

import com.example.appdbservice.entity.order.PriceSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceSaleRepository extends JpaRepository<PriceSale,Long> {

    Optional<PriceSale> getBySaleIdAndPriceLessThanEqual(Long sale_id, Double price);

}
