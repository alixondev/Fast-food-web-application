package com.example.appdbservice.repository.user;

import com.example.appdbservice.entity.users.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DistrictRepository extends JpaRepository<District, Long> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, UUID id);


    
}
