package com.example.appdbservice.entity.users;

import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "update address set deleted=true where id=?")
@Where(clause = "deleted=false")
public class Address extends AbsLongEntity {

    private double lat;

    private double lon;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private District district;

    @Column(nullable = false)
    private String target;

    private String photo;


}
