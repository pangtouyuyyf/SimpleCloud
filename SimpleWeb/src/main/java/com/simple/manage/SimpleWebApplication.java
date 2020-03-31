package com.simple.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SimpleWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleWebApplication.class, args);
    }

}
