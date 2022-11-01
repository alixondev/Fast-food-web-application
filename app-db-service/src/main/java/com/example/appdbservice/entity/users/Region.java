package com.example.appdbservice.entity.users;

import com.example.appdbservice.entity.enums.RegionEnum;
import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Region extends AbsLongEntity {

    @Enumerated(EnumType.STRING)
    private RegionEnum regionEnum;

}
