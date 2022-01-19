package com.test.toyproject1.service;

import com.test.toyproject1.entity.Item;
import com.test.toyproject1.entity.ItemBook;
import com.test.toyproject1.entity.Member;
import com.test.toyproject1.entity.Order;
import com.test.toyproject1.entity.valueType.Address;
import com.test.toyproject1.entity.valueType.OrderStatus;
import com.test.toyproject1.exception.NotEnoughStockException;
import com.test.toyproject1.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    /**
     * 테스트
     * 1. 상품주문 성공
     * 2. 상문 주문할 때 재고 수량 초과하면 안 된다.
     * 3. 주문 취소가 성공
     */

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문성공() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook("JPA입문", 15000, 5);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        //상품주문시 상태 ORDER
        assertEquals( OrderStatus.ORDER, getOrder.getStatus());
        // 주문 상품 2개
        assertEquals(orderCount, getOrder.getOrderItems().get(0).getCount());
        // 주문 가격은 가격 * 수량
        assertEquals(book.getPrice() * orderCount, getOrder.getTotalPrice());
        // 주문 수량만큼 재고 마이너스
        assertEquals(5 - orderCount, book.getStockQuantity());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook("JPA입문", 15000, 5);
        int orderCount = 10; // 5개 재고에 10개 주문

        //when
        assertThrows(NotEnoughStockException.class,
                () -> orderService.order(member.getId(), book.getId(), orderCount));
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook("JPA입문", 15000, 5);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        // 주문 취소 상태 검증
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        // 재고 원복 검증
        assertEquals(5, book.getStockQuantity());

    }

    private Member createMember() {
        Member member = new Member("회원1");
        member.setAddress(new Address("서울", "송파", "문정로 11길"));
        em.persist(member);
        return member;
    }

    private ItemBook createBook(String name, int price, int stockQuantity) {
        ItemBook book = new ItemBook();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }
}