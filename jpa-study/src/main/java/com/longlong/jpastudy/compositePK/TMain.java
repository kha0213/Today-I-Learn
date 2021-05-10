package com.longlong.jpastudy.compositePK;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-05-02
 * Time: 오후 11:07
 */
public class TMain {
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
        Parent parentA = new Parent();
        parentA.setName("ParentA");

        em.persist(parentA);

        Child childA = new Child();
        childA.setName("ChildA");
        childA.setParent(parentA);
        em.persist(childA);

        Child childB = new Child();
        childB.setName("ChildB");
        childB.setParent(parentA);
        em.persist(childB);

        GrandChild grandChildA = new GrandChild();
        grandChildA.setName("GrandChildA");
        grandChildA.setChild(childA);
        em.persist(grandChildA);

        GrandChild grandChildB = new GrandChild();
        grandChildB.setName("GrandChildB");
        grandChildB.setChild(childA);
        em.persist(grandChildB);

        GrandChild grandChildC = new GrandChild();
        grandChildC.setName("GrandChildC");
        grandChildC.setChild(childB);
        em.persist(grandChildC);


    }
}
