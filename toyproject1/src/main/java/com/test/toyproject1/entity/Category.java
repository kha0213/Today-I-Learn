package com.test.toyproject1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@Getter @Setter
@ToString
@Table(name = "CATEGORY")
@Entity
public class Category {

    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private List<Item> items;

    @ToString.Exclude
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PARENT_CATEGORY_ID")
    private Category parent;

    @ToString.Exclude
    @OneToMany(mappedBy = "parent")
    private List<Category> childList = new ArrayList<>();

    // 연관관계 편의 메소드
    public void addChildCategory(Category child) {
        this.childList.add(child);
        child.setParent(this);
    }
}