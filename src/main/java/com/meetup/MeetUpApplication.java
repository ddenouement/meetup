package com.meetup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MeetUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetUpApplication.class, args);
    }
}
