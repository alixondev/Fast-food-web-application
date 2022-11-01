package com.example.appdbservice.entity.order;

import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "delivery_sale")
@SQLDelete(sql = "update delivery_sale set deleted = true where id = ?")
@Where(clause = "deleted=false")
public class DeliveryFreeSale extends AbsLongEntity {

    private Double minPrice;  // necha puldan otsa

}
