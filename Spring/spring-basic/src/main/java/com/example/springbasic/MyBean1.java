package com.example.springbasic;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class MyBean1 {

    private final MyBean2 myBean2;

    @PostConstruct
    public void init() {
        System.out.println("MyBean1.init 시작");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("MyBean1.destroy 종료");
    }
}
