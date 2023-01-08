package com.example.springbasic;

import org.springframework.stereotype.Component;

public class MyBean2 {
    public void print() {
        System.out.println("MyBean2.print : " + this.getClass().getName());
    }
}
