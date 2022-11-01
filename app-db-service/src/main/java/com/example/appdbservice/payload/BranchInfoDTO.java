package com.example.appdbservice.payload;

import com.example.appdbservice.entity.users.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchInfoDTO {


    private Date startTime;

    private Date endTime;

    private Address address;

    private String branchPhoneNumber;



}
