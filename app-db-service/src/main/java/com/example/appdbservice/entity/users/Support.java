package com.example.appdbservice.entity.users;

import com.example.appdbservice.entity.enums.SupportType;
import com.example.appdbservice.entity.order.Branch;
import com.example.appdbservice.entity.product.Attachment;
import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Support extends AbsLongEntity {

    @Enumerated(EnumType.STRING)
    private SupportType supportType;

    private String title;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment attachment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch_id", insertable = false, updatable = false)
    private Branch branch;

    @Column(name = "branch_id")
    private Long branchId;

}
