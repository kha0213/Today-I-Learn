package com.longlong.jpastudy.vo;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id @GeneratedValue
    private Long id;

    private int orderPrice;

    private int count;
    @ManyToOne(fetch = FetchType.LAZY)
    private Orders order;
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    public OrderItem(int orderPrice, int count) {
        this.orderPrice = orderPrice;
        this.count = count;
    }

    /**
     *
     * @param item1
     */
    public void addItem(Item item1) {
        setItem(item1);
        item1.getOrderItems().add(this);
    }

    public void putOrder(Orders order) {
        setOrder(order);
        order.getOrderItems().add(this);
    }
}
