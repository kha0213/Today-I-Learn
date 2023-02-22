package com.example.springbasic.config;

import com.example.springbasic.MyBean1;
import com.example.springbasic.MyBean2;
import com.example.springbasic.MyBean3;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
public class BasicConfiguration {

    @Bean
    public MyBean1 myBean1(MyBean2 bean2) {
        return new MyBean1(bean2);
    }

    @Bean
    public MyBean2 myBean2() {
        System.out.println("BasicConfiguration.myBean2");
        return new MyBean2();
    }

    @Bean
    public MyBean3 myBean3(MyBean2 bean2) {
        return new MyBean3(bean2);
    }
}
