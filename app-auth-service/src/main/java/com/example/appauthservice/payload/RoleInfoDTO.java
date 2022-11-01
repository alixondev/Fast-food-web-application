package com.example.appauthservice.payload;

import com.example.appdbservice.entity.enums.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleInfoDTO {

    private String name;

    private Set<Permissions> permissionEnums;

}
