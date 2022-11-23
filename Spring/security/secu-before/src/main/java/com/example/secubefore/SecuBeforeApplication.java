package com.example.secubefore;

import config.SecurityConfigV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(SecurityConfigV1.class)
@SpringBootApplication
public class SecuBeforeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuBeforeApplication.class, args);
    }

}
