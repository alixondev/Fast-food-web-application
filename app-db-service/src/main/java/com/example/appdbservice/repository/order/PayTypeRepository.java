package com.example.appdbservice.repository.order;

import com.example.appdbservice.entity.order.PayType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayTypeRepository extends JpaRepository<PayType, Long> {


//    List<PayType> findAllByPayType(PayTypeEnum payType);
}
