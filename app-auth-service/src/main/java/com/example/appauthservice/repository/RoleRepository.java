package com.example.appauthservice.repository;

import com.example.appdbservice.entity.users.Role;
import com.example.appdbservice.payload.ApiResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

    boolean existsByNameAndDeletedFalse(String name);

    boolean existsByNameAndIdAndDeletedFalse(String name, Long id);


}
