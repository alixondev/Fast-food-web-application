package com.example.appauthservice.payload;

import com.example.appdbservice.entity.users.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Long roleId;

    public UserInfoDTO(Role role) {

    }
}
