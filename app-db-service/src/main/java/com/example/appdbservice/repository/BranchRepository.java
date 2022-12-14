package com.example.appdbservice.repository;

import com.example.appdbservice.entity.order.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch,Long> {

    Optional<Branch> findByAddress(String address);
}
