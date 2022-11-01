package com.example.appdbservice.entity.users;

import com.example.appdbservice.entity.enums.Permissions;
import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "roles")
public class Role extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<Permissions> permissions;

}
