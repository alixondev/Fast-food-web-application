package com.example.appauthservice.payload;

import com.example.appdbservice.entity.enums.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleAddDTO {

    @NotBlank(message = "Name bo'sh bo'lmasligi kerak!")
    private String name;

    @NotEmpty(message = "Permission bo'sh bo'lmasligi kerak!")
    private Set<Permissions> permissions;
}
