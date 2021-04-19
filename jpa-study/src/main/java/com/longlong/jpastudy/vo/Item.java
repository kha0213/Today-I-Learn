package com.longlong.jpastudy.vo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id @GeneratedValue
    private Long id; // item_id

    private String name;

    private int price;

    private int stockQuantity;

    @OneToMany(mappedBy = "id")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;

    }
}
