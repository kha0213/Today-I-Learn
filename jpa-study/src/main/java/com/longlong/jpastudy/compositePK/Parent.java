package com.longlong.jpastudy.compositePK;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Kim Young Long.
 * Date : 2021-05-03.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@Entity
@Getter
@Setter
public class Parent {
    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;
    private String name;
}
