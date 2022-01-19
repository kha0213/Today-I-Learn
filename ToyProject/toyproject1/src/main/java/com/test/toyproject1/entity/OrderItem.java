package com.test.toyproject1.entity;

import com.test.toyproject1.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@NoArgsConstructor
@Getter @Setter
@ToString
@Table(name = "ORDER_ITEM")
@Entity
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ToString.Exclude
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice;

    private int count;

    //==생성 메소드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) throws NotEnoughStockException {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);;
        return orderItem;
    }
    //==비즈니스 로직

    /**
     * 주문 취소
     */
    public void cancel() {
        getItem().addStock(count);
    }

    /**
     * 주문상품 전체 가격 조회
     * @return
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}