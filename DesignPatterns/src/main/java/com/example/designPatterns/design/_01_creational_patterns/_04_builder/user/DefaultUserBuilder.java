package com.example.design._01_creational_patterns._04_builder.user;

public class DefaultUserBuilder implements UserBuilder {
    private User user = new User();

    @Override
    public UserBuilder id(int id) {
        user.setId(id);
        return this;
    }

    @Override
    public UserBuilder name(String name) {
        user.setName(name);
        return this;
    }

    @Override
    public UserBuilder height(int height) {
        user.setHeight(height);
        return this;
    }

    @Override
    public UserBuilder weight(int weight) {
        user.setWeight(weight);
        return this;
    }

    @Override
    public User build() {
        return user;
    }
}
