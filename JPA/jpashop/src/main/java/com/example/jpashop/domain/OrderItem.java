package com.example.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "order_item")
@Getter
@Setter
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
}
