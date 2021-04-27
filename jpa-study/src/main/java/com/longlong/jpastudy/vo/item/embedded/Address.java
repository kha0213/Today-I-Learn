package com.longlong.jpastudy.vo.item.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by Kim Young Long.
 * Date : 2021-04-27.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
