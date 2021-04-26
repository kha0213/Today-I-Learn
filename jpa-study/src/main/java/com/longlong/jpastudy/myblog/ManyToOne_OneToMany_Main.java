package com.longlong.jpastudy.myblog;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class ManyToOne_OneToMany_Main {
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

        Teacher teacherA = new Teacher("teacherA");
        //teacherA.setSubject(math);
        teacherA.addSubject(math);
        em.persist(teacherA);
        Teacher teacherB = new Teacher("teacherB");
        //teacherB.setSubject(math);
        teacherB.addSubject(math);
        em.persist(teacherB);

        em.remove(teacherA);

        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println("teacherA = " + teacherA + " subject : " +teacherA.getSubject());
        System.out.println("teacherB = " + teacherB + " subject : " +teacherB.getSubject().hashCode());
        System.out.println("math = " + math);
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    }


}
