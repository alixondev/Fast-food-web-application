package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressAddDTO {

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;

    @NotNull(message = "Full address must not be null!")
    private String fullAddress;

    @NotNull
    private Long districtId;

}
