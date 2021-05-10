package com.longlong.jpastudy.compositePK;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kim Young Long.
 * Date : 2021-05-03.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@Getter
@Setter
public class GrandChildId implements Serializable {

    private Long id;

    private Child child;
}
