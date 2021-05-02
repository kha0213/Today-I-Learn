package com.longlong.jpastudy.myblog;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class OneToOne_Main {
    public static void main(String[] args) {
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println("start!!");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("simple-jpa-application");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("■■■■■■■■■■■■■■■■■■■■ ERROR   : " + e.getMessage());
        } finally {
            em.close();
        }

        emf.close();
    }

    public static void logic(EntityManager em) throws Exception {
        Student studentA = new Student("studentA");
        em.persist(studentA);
        Student studentB = new Student("studentB");
        em.persist(studentB);
        AddressByMyBlog address1 = new AddressByMyBlog("munjeoug", "158-200", "seoul");
        em.persist(address1);
        AddressByMyBlog address2 = new AddressByMyBlog("garak", "129-120", "seoul");
        em.persist(address2);
        studentA.setAddress(address1);
        studentB.setAddress(address2);


        em.flush();
        em.clear();

        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println("studentA = " + studentA);
        System.out.println("studentB = " + studentB);
        System.out.println("address1 = " + address1);
        System.out.println("address2 = " + address2);
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    }


}
