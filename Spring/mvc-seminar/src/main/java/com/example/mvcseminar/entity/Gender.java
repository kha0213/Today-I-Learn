package com.example.mvcseminar.entity;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("M"), FEMALE("F");

    private final String value;
    Gender(String value) {
        this.value = value;
    }
}
