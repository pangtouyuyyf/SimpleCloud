package com.simple.auto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@MapperScan(basePackages = "com.simple.access")
public class SimpleAutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleAutoApplication.class, args);
    }

}
