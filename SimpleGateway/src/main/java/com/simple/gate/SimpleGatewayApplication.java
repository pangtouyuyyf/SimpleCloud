package com.simple.gate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SimpleGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleGatewayApplication.class, args);
    }

}
