package com.longlong.jpastudy.vo;

import com.longlong.jpastudy.vo.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
public class Category {
    @Id @GeneratedValue
    @Column(name = "Category_Id")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "parent")
    private List<Category> child;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;
}
