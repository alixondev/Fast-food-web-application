package com.example.appdbservice.entity.product;

import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "update attachment_content set deleted=true where id=?")
@Where(clause = "deleted=false")
public class AttachmentContent extends AbsLongEntity {

    private byte[] bytes;


    @OneToOne(optional = false)
    private Attachment attachment;
}
