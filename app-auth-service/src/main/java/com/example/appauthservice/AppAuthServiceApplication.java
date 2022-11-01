package com.example.appauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EntityScan(basePackages = "com.example.appdbservice.entity")                   //db libdagi entitylarni ko'rish uchun qo'yildi
//@EnableJpaRepositories(basePackages = "com.example.appdbservice.repository")
public class AppAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppAuthServiceApplication.class, args);
    }

}
