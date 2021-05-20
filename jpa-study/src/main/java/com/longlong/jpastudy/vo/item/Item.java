package com.longlong.jpastudy.vo.item;

import com.longlong.jpastudy.vo.BaseEntity;
import com.longlong.jpastudy.vo.Category;
import com.longlong.jpastudy.vo.OrderItem;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public class Item extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "Item_Id")
    private Long id; // item_id

    private String name;

    private int price;

    private int stockQuantity;

//    @ManyToMany
//    @JoinTable(
//        name = "CATEGORY_ITEM",
//        joinColumns = @JoinColumn(name = "Category_Id")
//    )
//    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

//    public void addCategory(Category category) {
//        if(!categories.contains(category))
//            categories.add(category);
//    }
}
