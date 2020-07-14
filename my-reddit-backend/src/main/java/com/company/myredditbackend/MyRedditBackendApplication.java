package com.company.myredditbackend;

import com.company.myredditbackend.configuration.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class MyRedditBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRedditBackendApplication.class, args);
    }

}
