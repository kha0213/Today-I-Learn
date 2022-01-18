package com.longlong.jpastudy.javaApp.onlyJava;

import com.longlong.jpastudy.javaApp.config.Status;
import com.longlong.jpastudy.vo.item.Item;
import com.longlong.jpastudy.vo.Member;
import com.longlong.jpastudy.vo.Orders;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Chapter05_JpaMain {
    public static void main(String[] args) {
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println("start!!");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("simple-jpa-application");
        EntityManager em = emf.createEntityManager();


        System.out.println("■■■■■■■■■■■■■■■■■■■■시작~~~~~");

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em, tx);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("■■■■■■■■■■■■■■■■■■■■ ERROR   : " + e.getMessage());
        } finally {
            em.close();

        }

        emf.close();
    }

    public static void logic(EntityManager em, EntityTransaction tx) throws Exception {
        Member memberA = new Member("memberA","city1","street1","zipcode1");
        em.persist(memberA);
        Member memberB = new Member("memberB","city2","street2","zipcode2");
        em.persist(memberB);

        Orders orderA = new Orders(LocalDateTime.of(2021, 3, 9, 12, 30), Status.DELIVERY);
        orderA.putMember(memberA);
        //orderA.setMember(memberA);
        em.persist(orderA);

        Orders orderB = new Orders(LocalDateTime.now(), Status.PREPARE);
        orderB.putMember(memberA);
        //orderB.setMember(memberA);
        em.persist(orderB);

        Item item1 = new Item("item1",10000,1000);
        em.persist(item1);
        Item item2 = new Item("item2",20000,1000);
        em.persist(item2);
        Item item3 = new Item("item3",1000,1000);
        em.persist(item3);

//        OrderItem orderItem1 = new OrderItem(10000,2);
//        orderItem1.addItem(item1);
//        //orderItem1.setItem(item1);
//        orderItem1.putOrder(orderA);
//        em.persist(orderItem1);
//
//        OrderItem orderItem2 = new OrderItem(20000,3);
//        orderItem2.addItem(item2);
//        // orderItem2.setItem(item2);
//        orderItem2.putOrder(orderA);
//        em.persist(orderItem2);
//
//        OrderItem orderItem3 = new OrderItem(1000,300);
//        orderItem3.addItem(item3);
//        //orderItem3.setItem(item3);
//        orderItem3.putOrder(orderB);
//        em.persist(orderItem3);






    }


}
