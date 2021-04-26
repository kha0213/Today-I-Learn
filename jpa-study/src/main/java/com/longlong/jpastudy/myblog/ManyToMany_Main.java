package com.longlong.jpastudy.myblog;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class ManyToMany_Main {
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
        Subject math = new Subject("math");
        em.persist(math);
        Subject english = new Subject("english");
        em.persist(english);

        Student studentA = new Student("studentA");
        em.persist(studentA);
        Student studentB = new Student("studentB");
        em.persist(studentB);

        studentA.addSubject(math);
        studentA.addSubject(english);
        studentB.addSubject(english);

        em.flush();
        em.clear();

        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println("studentA = " + studentA);
        System.out.println("studentB = " + studentB);
        System.out.println("math = " + math);
        System.out.println("english = " + english);
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    }


}
