package com.longlong.jpastudy.ch16_lock;

import com.longlong.jpastudy.dto.TeacherDto;
import com.longlong.jpastudy.javaApp.myblog.DbSetting;
import com.longlong.jpastudy.javaApp.myblog.Student;
import com.longlong.jpastudy.javaApp.myblog.Teacher;
import org.springframework.scheduling.config.Task;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 2021-07-28
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
public class LockMain {
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

    private static void logic(EntityManager em) {
        User user1 = new User("user1");
        em.persist(user1);
        System.out.println("처음 유저 = " + user1.getVersion());
        User findUser1 = em.find(User.class, user1.getId(), LockModeType.NONE);
        findUser1.setName("user2");
        em.flush();
        em.clear();
        User findUser2 = em.find(User.class, user1.getId());
        System.out.println("한 번 수정 = " + findUser2.getVersion());


    }

}
