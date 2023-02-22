package com.example.springbasic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class MyBean3 {

    private final MyBean2 myBean2;

    public void print() {
        System.out.println("MyBean3.print : " + this.getClass().getName());
    }
}
