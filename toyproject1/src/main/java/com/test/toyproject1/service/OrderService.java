package com.test.toyproject1.service;

import com.test.toyproject1.entity.*;
import com.test.toyproject1.exception.NotEnoughStockException;
import com.test.toyproject1.repository.ItemRepository;
import com.test.toyproject1.repository.MemberRepository;
import com.test.toyproject1.repository.OrderRepository;
import com.test.toyproject1.search.OrderSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;

    /**
     * 주문
     * @param memberId
     * @param itemId
     * @param count
     * @return
     */
    public Long order(Long memberId, Long itemId, int count) throws NotEnoughStockException {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery(member.getAddress());
        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 주문 취소
     */
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    /**
     * 주문 검색
     * @param orderSearch
     * @return
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
