package com.simple.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.simple")
@MapperScan(basePackages = "com.simple")
public class SimpleAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleAuthApplication.class, args);
    }

}
