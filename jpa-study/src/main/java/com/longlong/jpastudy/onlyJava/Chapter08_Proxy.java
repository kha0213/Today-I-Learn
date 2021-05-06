package com.longlong.jpastudy.onlyJava;

import com.longlong.jpastudy.config.Status;
import com.longlong.jpastudy.vo.Delivery;
import com.longlong.jpastudy.vo.Member;
import com.longlong.jpastudy.vo.Orders;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-05-06
 * Time: 오후 10:09
 */
public class Chapter08_Proxy {
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

        Orders order1 = new Orders(LocalDateTime.now(), Status.PREPARE);
        em.persist(order1);

        Member member = new Member();
        member.addOrder(order1);
        em.persist(member);

        em.flush();
        em.clear();

        Orders orders = em.find(Orders.class, order1.getId());

        System.out.println("orders = " + orders.getClass().getName());
        System.out.println("orders.getMember() = " + orders.getMember().getClass().getName());
    }
}
