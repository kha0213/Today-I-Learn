package com.example.jpashop.repository;

import com.example.jpashop.domain.Order;
import com.example.jpashop.vo.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        String jpql = "select o from Order o join o.member m" +
                " where 1=1 ";
        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            jpql += " and o.status = '" + orderSearch.getOrderStatus() + "'";
        }
        // 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            jpql += " and o.member.name like '%" + orderSearch.getMemberName() + "%'";
        }
        return em.createQuery(
                jpql
                ,Order.class)
                .setMaxResults(1000)
                .getResultList();
    }
}
