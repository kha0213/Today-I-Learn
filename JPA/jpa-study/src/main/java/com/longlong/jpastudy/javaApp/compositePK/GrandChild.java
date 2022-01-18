package com.longlong.jpastudy.javaApp.compositePK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Young Long.
 * Date : 2021-05-03.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(GrandChildId.class)
public class GrandChild {
    @Id @GeneratedValue
    @Column(name = "GRNADCHILD_ID")
    private Long id;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "CHILD_ID"),
            @JoinColumn(name = "PARENT_ID")
    })
    private Child child;

    private String name;
}
