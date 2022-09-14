package com.example.jpashop.domain;

import lombok.*;

import javax.persistence.Embeddable;

import static lombok.AccessLevel.PROTECTED;

/**
 * value type
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
