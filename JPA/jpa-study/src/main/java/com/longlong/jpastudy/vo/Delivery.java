package com.longlong.jpastudy.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Kim Young Long.
 * Date : 2021-04-26.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
//@Entity
@Getter
@Setter
@NoArgsConstructor
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "Delivery_Id")
    private Long id;

    private String city;

    private String street;

    private String zipcode;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "delivery")
    private Orders order;

    public Delivery(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
