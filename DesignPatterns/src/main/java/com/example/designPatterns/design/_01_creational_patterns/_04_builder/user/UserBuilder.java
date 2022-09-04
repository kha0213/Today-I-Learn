package com.example.design._01_creational_patterns._04_builder.user;

public interface UserBuilder {
    UserBuilder id(int id);
    UserBuilder name(String name);
    UserBuilder height(int height);
    UserBuilder weight(int weight);
    User build();
}
