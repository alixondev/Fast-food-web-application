package com.example.appdbservice.repository;

import com.example.appdbservice.entity.product.RecommendedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendedProductRepository extends JpaRepository<RecommendedProduct,Long> {

    boolean existsByCategoryIdAndProductId(Long category_id, Long product_id);

    List<RecommendedProduct> findAllByCategoryId(Long category_id);

}
