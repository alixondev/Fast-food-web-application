package com.example.appdbservice.repository.order;

import com.example.appdbservice.entity.order.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketProductRepository extends JpaRepository<BasketProduct,Long> {

    Optional<BasketProduct> findByBasketIdAndProductId(Long basket_id, Long product_id);

}
