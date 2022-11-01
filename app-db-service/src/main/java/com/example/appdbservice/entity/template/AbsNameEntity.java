package com.example.appdbservice.entity.template;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class AbsNameEntity extends AbsLongEntity {

    @Column(nullable = false)
    private String uzName;

    @Column(nullable = false)
    private String ruName;

    private boolean active = false;


    public AbsNameEntity(String nameUz, String nameRu) {
        this.ruName = nameRu;
        this.uzName = nameUz;
    }

}
