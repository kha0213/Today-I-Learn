package com.longlong.jpastudy.onlyJava;

import com.longlong.jpastudy.vo.Member;
import com.longlong.jpastudy.vo.Orders;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Chapter09_JpaMain {
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
        Member memberA = new Member("Long~", "city", "street", "zipcode");
        em.persist(memberA);
    }
}
