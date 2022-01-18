package com.longlong.jpastudy.javaApp.onlyJava;

import com.longlong.jpastudy.vo.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Chapter08_JpaMain {
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
        // order에 cascade 걸어야 한다.
//        Orders order = new Orders();
//        em.persist(order);
//        Delivery delivery = new Delivery();
//        order.setDelivery(delivery);
        Member memberA = new Member();
        memberA.setName("memberA");

        Orders orderA = new Orders();
        Orders orderB = new Orders();
        memberA.addOrder(orderA);
        memberA.addOrder(orderB);
        em.persist(memberA);

        memberA.getOrders().remove(orderA);
        for (Orders order : memberA.getOrders()) {
            System.out.println("order = " + order);
        }
    }
}
