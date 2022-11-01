package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressForBranchDTO {

    private Double lat;

    private Double lon;

    private String fullAddress;

}
