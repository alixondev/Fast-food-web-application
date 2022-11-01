package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchAddDTO {

    private Date startTime;

    private Date endTime;

    private String address;

    private String branchPhoneNumber;
}
