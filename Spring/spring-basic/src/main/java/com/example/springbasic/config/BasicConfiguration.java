package com.example.springbasic.config;

import com.example.springbasic.MyBean1;
import com.example.springbasic.MyBean2;
import com.example.springbasic.MyBean3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicConfiguration {
    @Bean
    public MyBean2 myBean2() {
        System.out.println("BasicConfiguration.myBean2");
        return new MyBean2();
    }

    @Bean
    public MyBean1 myBean1() {
        return new MyBean1(myBean2());
    }

    @Bean
    public MyBean3 myBean3() {
        return new MyBean3(myBean2());
    }
}
