package com.example.appdbservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeliverySaleInfoDTO {

    private Double price;

    private Date startDate;

    private Date endDate;

    private Time startTime;

    private Time endTime;

}
