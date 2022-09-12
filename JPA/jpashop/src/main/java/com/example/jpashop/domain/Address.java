package com.example.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

/**
 * value type
 */
@Data
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
