package com.longlong.jpastudy.javaApp.compositePK;

import lombok.*;

import java.io.Serializable;

/**
 * Created by Kim Young Long.
 * Date : 2021-05-03.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChildId implements Serializable {
    private Long id;
    private Parent parent;
}
