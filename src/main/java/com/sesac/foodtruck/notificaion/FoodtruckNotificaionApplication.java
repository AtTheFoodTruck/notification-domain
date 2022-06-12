package com.sesac.foodtruck.notificaion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableJpaAuditing
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class FoodtruckNotificaionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodtruckNotificaionApplication.class, args);
    }

}
