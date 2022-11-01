package com.example.appauthservice.repository;

import com.example.appdbservice.entity.users.User;
import com.example.appdbservice.payload.ApiResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    ApiResult findByPhoneNumber(String username);

}
