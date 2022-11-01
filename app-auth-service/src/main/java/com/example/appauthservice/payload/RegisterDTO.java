package com.example.appauthservice.payload;

import com.example.appdbservice.entity.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDTO {

    @NotBlank(message = "Ismingizni kiritmadingiz!")
    @Size(min = 3, max = 100, message = "Ismingizni uzunroq kiriting(3-100)")
    private String firstName;

    private String lastName;

    @NotBlank(message = "Telefon raqamini kiritmadingiz!")
    @Pattern(regexp = "[+][9][9][8][0-9]{9}", message = "Telefon raqamining formatini xato kiritdingiz!")
    private String phoneNumber;

    @NotNull(message = "District bo'sh bo'lmasligi kerak")
    private Long addressId;

    private Long avatarId;

    @NotBlank(message = "Password bo'sh bo'lmasligi kerak")
    private String password;


    @NotNull(message = "Tizim tili bo'sh bo'lmasligi kerak")
    private LanguageEnum language;

}
