package com.example.appdbservice.repository.order;

import com.example.appdbservice.entity.order.CountSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCountBonusRepository extends JpaRepository<CountSale, Long> {

}
