package com.example.springbasic;

import org.springframework.stereotype.Component;

@Component
public class MyBean1 {
    public void print() {
        System.out.println("MyBean1.print : " + this.getClass().getName());
    }
}
