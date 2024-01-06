package com.example.appjam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AppJamApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppJamApplication.class, args);
    }

}
