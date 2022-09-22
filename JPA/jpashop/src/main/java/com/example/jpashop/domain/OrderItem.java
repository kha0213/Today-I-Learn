package com.example.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class OrderItem extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private Item item;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="order_id")
    private Order order;
    private Integer orderPrice;
    private Integer count;

    /**
     * 상품 취소
     */
    public void cancel() {
        getItem().addStock(count);
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }
}
