package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@EnableBatchProcessing
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        int exit = SpringApplication.exit(SpringApplication.run(DemoApplication.class, args));
        log.info("exit = {}", exit);
        System.exit(exit);
    }
}
