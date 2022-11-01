package com.example.appdbservice.payload;

import com.example.appdbservice.entity.enums.SaleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleAddDTO {

    @NotEmpty(message = "{SALE_NAME_NOT_EMPTY}")
    private String name;

    private String description;

    @NotNull(message = "{SALE_TYPE_NOT_NULL}")
    private SaleTypeEnum type;


    @NotNull(message = "{SALE_AMOUNT_NOT_NULL}")
    private Double amount;

    @NotNull(message = "{Start_time_must_not_be_null!}")
    private Timestamp startTime;

    @NotNull(message = "{End_time_must_not_be_null!}")
    private Timestamp endTime;

    @NotNull(message = "{Start_date_must_not_be_null!}")
    private Date startDate;

    @NotNull(message = "{End_date_must_not_be_null!}")
    private Date endDate;

}
