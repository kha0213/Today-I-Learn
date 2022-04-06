package com.example.design._01_creational_patterns._02_factory_method.pizza;

public class DeluxePizza extends Pizza {
    @Override
    public void order() {
        System.out.println("DeluxePizza order!!!");
    }
}
