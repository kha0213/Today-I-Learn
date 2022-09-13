package com.example.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@Setter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @ToString.Exclude
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
