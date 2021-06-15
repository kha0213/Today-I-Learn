package com.test.toyproject1.entity;

import com.test.toyproject1.entity.valueType.Address;
import com.test.toyproject1.entity.valueType.DeliveryStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@ToString
@Getter @Setter
@Table(name = "DELIVERY")
@Entity
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @ToString.Exclude
    @OneToOne(fetch = LAZY, mappedBy = "delivery")
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public Delivery(Address address) {
        this.address = address;
    }
}