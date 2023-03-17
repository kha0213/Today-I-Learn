package com.example.validation;

public record User(String name, int age) {
    public User {
        if (age < 0) {
            throw new IllegalArgumentException("age must be greater than 0");
        }
    }
}
