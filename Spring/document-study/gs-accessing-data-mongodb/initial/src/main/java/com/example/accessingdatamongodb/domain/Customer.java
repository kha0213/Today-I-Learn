package com.example.accessingdatamongodb.domain;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
public class Customer {
    @Id
    private String id;

    private final String firstName;

    private final String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
