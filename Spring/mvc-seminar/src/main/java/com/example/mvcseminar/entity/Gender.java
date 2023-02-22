package com.example.mvcseminar.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender {
    MALE("M"), FEMALE("F");

    private final String value;
    Gender(String value) {
        this.value = value;
    }

    public static Gender of(String value) {
        return Arrays
                .stream(Gender.values())
                .filter(g -> g.value.equals(value))
                .findAny().orElseThrow();
    }
}
