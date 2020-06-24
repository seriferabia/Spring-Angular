package com.company.myredditbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MyRedditBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRedditBackendApplication.class, args);
    }

}
