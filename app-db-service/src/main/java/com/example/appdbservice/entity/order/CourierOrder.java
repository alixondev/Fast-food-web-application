package com.example.appdbservice.entity.order;

import com.example.appdbservice.entity.template.AbsLongEntity;
import com.example.appdbservice.entity.users.User;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@SQLDelete(sql = "update courier_order set deleted=true where id=?")
@Where(clause = "deleted=false")
@TypeDefs({  //bu databaseda string [] berilganda uni varcharga o'girib beradi
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        )
})
public class CourierOrder extends AbsLongEntity {
    @JoinColumn(name = "courier_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User courier;

    @Column(name = "courier_id")
    private Long courierId;

    @Type(type = "string-array")
    @Column(columnDefinition = "text[]", name = "orders")
    private String[] orders;

    @Column(nullable = false)
    private Boolean close = false;
}
