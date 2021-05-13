package com.longlong.jpastudy.javaApp.onlyJava;

import com.longlong.jpastudy.vo.Member;
import com.longlong.jpastudy.vo.item.embedded.Address;

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
        Address addressA = new Address("서울", "문정로", "158-2");
        Address addressB = new Address("부산", "겸지로", "290-22");
        Address addressC = new Address("LA", "갈비", "맛있다.");
        memberA.getAddressHistory().add(addressA);
        memberA.getAddressHistory().add(addressB);
        memberA.getAddressHistory().add(addressC);
    }
}
