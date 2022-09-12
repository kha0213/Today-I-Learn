package com.example.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@Setter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Address address;
}
