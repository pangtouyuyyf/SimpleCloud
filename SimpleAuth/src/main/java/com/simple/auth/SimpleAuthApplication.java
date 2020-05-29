package com.simple.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SimpleAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleAuthApplication.class, args);
    }

}
