package com.example.appdbservice.entity.order;

import com.example.appdbservice.entity.enums.SaleTypeEnum;
import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "update sale set deleted = true, active = false where id = ?")
@Where(clause = "deleted=false")
public class Sale extends AbsLongEntity {


    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    private SaleTypeEnum saleType;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Timestamp startTime;

    @Column(nullable = false)
    private Timestamp endTime;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

}
