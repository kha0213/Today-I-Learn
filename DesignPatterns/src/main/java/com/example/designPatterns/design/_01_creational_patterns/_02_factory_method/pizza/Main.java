package com.example.design._01_creational_patterns._02_factory_method.pizza;

import org.springframework.beans.factory.BeanFactory;

public class Main {
    public static void main(String[] args) {
        Pizza deluxePizza = Pizza.PizzaFactory(Pizza.PizzaType.Deluxe);
        deluxePizza.order();
    }
}
