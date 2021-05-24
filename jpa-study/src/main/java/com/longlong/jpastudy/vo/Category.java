package com.longlong.jpastudy.vo;

import com.longlong.jpastudy.dto.CategoryHierarchicalDto;
import com.longlong.jpastudy.vo.item.Item;
import lombok.*;

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
@TableGenerator(
        name = "Category_Generator",
        table = "JPA_Sequence",
        pkColumnValue = "Category_Seq",
        allocationSize = 1
)
@SqlResultSetMapping(
        name="CategoryHierarchicalDto",
        classes = @ConstructorResult(
                targetClass = CategoryHierarchicalDto.class,
                columns = {
                        @ColumnResult(name="category_id", type = Long.class),
                        @ColumnResult(name="name", type = String.class),
                        @ColumnResult(name="level", type = Integer.class),
                        @ColumnResult(name="out_name", type = String.class),
                        @ColumnResult(name="parent_category_id", type = Long.class)
                })
)
@Getter
@NoArgsConstructor
//@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "Category_Generator")
    @Column(name = "Category_Id")
    private Long id;

    private String name;

    @Column(name = "levels")
    private int levels;

//    @ManyToMany(mappedBy = "categories")
//    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "parent")
    private List<Category> child;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;


    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.levels = parent.levels + 1;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "[Category]" +
                "id=" + id +
                ", name=" + name +
                ", level=" + levels +
                ", parent.id=" + (parent==null?null:parent.id) +
                ", child.size=" + (child==null?0:child.size())
                ;
    }
}
