package com.longlong.jpastudy.onlyJava;

import com.longlong.jpastudy.dto.TeacherDto;
import com.longlong.jpastudy.javaApp.myblog.DbSetting;
import com.longlong.jpastudy.javaApp.myblog.Student;
import com.longlong.jpastudy.javaApp.myblog.Teacher;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-05-29
 * Time: 오전 7:22
 */
public class Chapter10_Jpql {
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
            DbSetting.setting(em);
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
        em.flush();
        em.clear();

        final List<Teacher> teacherList =
                em.createQuery("select t from Teacher as t", Teacher.class).getResultList();
        print(teacherList);

        String dtoQuery = "select new com.longlong.jpastudy.dto.TeacherDto(t.id, t.name) from Teacher as t";
        final List<TeacherDto> resultList =
                em.createQuery(dtoQuery, TeacherDto.class).getResultList();
        print(resultList);

        String orderByQuery = "select s from Student as s order by s.id asc";
        final List<Student> studentList =
                em.createQuery(orderByQuery, Student.class)
                        .setFirstResult(3)
                        .setMaxResults(5)
                        .getResultList()
                ;
        print(studentList);

    }

    private static void print(List<?> list) {
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        list.stream().forEachOrdered(System.out::println);
    }


}
