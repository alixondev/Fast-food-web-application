package com.example.appdbservice.entity.order;

import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class DeliveryPrice extends AbsLongEntity {

    private Double minPrice; // min nechi pul

    private Double minKM; //  masalan 4km gacha 9000so'm

    private Double priceEveryKm; // masalan 2km dan 4km dan o'tsa

    @OneToOne(fetch = FetchType.LAZY)
    private Branch branch;

}
