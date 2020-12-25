package com.simple.game;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@MapperScan(basePackages = "com.simple.access")
public class SimpleGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleGameApplication.class, args);
    }

}
