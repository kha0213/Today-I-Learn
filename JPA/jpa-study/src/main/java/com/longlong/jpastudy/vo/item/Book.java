package com.longlong.jpastudy.vo.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
@DiscriminatorValue("BOOK")
public class Book extends Item {
    private String author;
    private String isbn;
}
