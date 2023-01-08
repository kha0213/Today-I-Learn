package com.example.springbasic.config;

import com.example.springbasic.MyBean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicConfiguration {
    @Bean
    public MyBean2 myBean2() {
        return new MyBean2();
    }
}
