package com.example.appdbservice.repository.user;

import com.example.appdbservice.entity.users.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
//    Optional<Region> findByName(RegionEnum name);

}
