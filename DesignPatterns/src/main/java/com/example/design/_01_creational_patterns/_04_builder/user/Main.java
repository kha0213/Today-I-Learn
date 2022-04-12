package com.example.design._01_creational_patterns._04_builder.user;

public class Main {
    public static void main(String[] args) {
        UserBuilder builder = new DefaultUserBuilder();
        User 롱롱이 = builder.id(1)
                .height(160)
                .weight(70)
                .name("롱롱이")
                .build();
        System.out.println("롱롱이 = " + 롱롱이);
    }
}
