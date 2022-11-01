package com.example.appdbservice.entity.order;

import com.example.appdbservice.entity.enums.PayTypeEnum;
import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "pay_type")
public class PayType extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private PayTypeEnum enumType;

}
