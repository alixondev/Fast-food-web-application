package com.example.appdbservice.entity.product;

import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "update attachment set deleted=true where id=?")
@Where(clause = "deleted=false")
public class Attachment extends AbsLongEntity {

    private String originalName;

    private String contentType;

    private long size;

    private String name;

    public Attachment(String originalName, long size, String contentType) {
        this.originalName = originalName;
        this.size = size;
        this.contentType = contentType;
    }
}
