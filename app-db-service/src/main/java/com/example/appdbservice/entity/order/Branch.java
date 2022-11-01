package com.example.appdbservice.entity.order;

import com.example.appdbservice.entity.template.AbsLongEntity;
import com.example.appdbservice.entity.users.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
//@AllArgsConstructor
@Entity(name = "branch")
@Getter
@Setter
@ToString
public class Branch extends AbsLongEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Address address;

    @Temporal(TemporalType.TIME)
    private java.util.Date startWorkingTime;

    @Temporal(TemporalType.DATE)
    private java.util.Date endWorkingTime;

    @Column(nullable = false)
    private String branchPhoneNumber;    // filialning nomeri

    public Branch(Address address, Date startTime, Date endTime, String branchPhoneNumber) {
        this.address = address;
        this.startWorkingTime = startTime;
        this.endWorkingTime = endTime;
        this.branchPhoneNumber = branchPhoneNumber;
    }

    public Branch(String address, java.util.Date startTime, java.util.Date endTime, String branchPhoneNumber) {


    }

    public void setAddress(String address) {

    }

}
