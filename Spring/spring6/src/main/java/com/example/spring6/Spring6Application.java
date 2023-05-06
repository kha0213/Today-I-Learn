package com.example.spring6;

import com.example.spring6.entity.ExchangeResponse;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootApplication
public class Spring6Application {

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            RestTemplate build = new RestTemplate();
            Map<String, Double> forObject =
                    build.getForObject("https://open.er-api.com/v6/latest", Map.class).get("rates");

            System.out.println(forObject);
        };
    }



    public static void main(String[] args) {
        SpringApplication.run(Spring6Application.class, args);
    }

}
