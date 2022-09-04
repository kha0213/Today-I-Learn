package com.example.design._01_creational_patterns._02_factory_method.pizza;

public abstract class Pizza {
    public abstract void order();

    public enum PizzaType {
        HamMushroom, Deluxe, Seafood
    }

    public static Pizza PizzaFactory(PizzaType pizzaType) {
        switch (pizzaType) {
            case HamMushroom:
                return new HamMushroomPizza();
            case Deluxe:
                return new DeluxePizza();
            case Seafood:
                return new SeafoodPizza();
        }
        throw new IllegalStateException("The pizza type is not recognized");
    }
}
