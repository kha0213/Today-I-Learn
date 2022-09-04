package com.example.design._01_creational_patterns._02_factory_method.pizza;

public class SeafoodPizza extends Pizza {
    @Override
    public void order() {
        System.out.println("SeafoodPizza order!!!");
    }
}
