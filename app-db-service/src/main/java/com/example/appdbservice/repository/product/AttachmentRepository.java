package com.example.appdbservice.repository.product;

import com.example.appdbservice.entity.product.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    
}
