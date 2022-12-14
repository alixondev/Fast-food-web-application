package com.example.appauthservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInDTO {

    @NotNull(message = "Telefon raqamini kiritmadingiz!" )
    @Pattern(regexp = "[+][9][9][8][0-9]{9}",message = "Telefon raqamining formatini xato kiritdingiz!")
    private String username;
    @NotBlank(message = "Parol bo`sh bo`lmasligi kerak!")
    private String password;

}
