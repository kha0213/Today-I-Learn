package com.example.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Delivery extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(STRING)
    private DeliveryStatus status;

}
