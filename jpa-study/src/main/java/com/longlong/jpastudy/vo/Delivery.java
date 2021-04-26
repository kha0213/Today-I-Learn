package com.longlong.jpastudy.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Kim Young Long.
 * Date : 2021-04-26.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@Entity
@Getter
@Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "Delivery_Id")
    private Long id;

    private String city;

    private String street;

    private String zipcode;

    @OneToOne(mappedBy = "delivery")
    private Orders order;
}
