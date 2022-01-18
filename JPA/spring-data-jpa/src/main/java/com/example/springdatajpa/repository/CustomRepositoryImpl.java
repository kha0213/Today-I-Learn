package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CustomRepositoryImpl implements CustomRepository {
    @Autowired
    EntityManager em;

    @Override
    public Teacher findCustomById(Long id) {
        System.out.println("========================");
        System.out.println("now CustomRepositoryImpl");
        System.out.println("========================");
        return em.createQuery("select t from Teacher as t where t.id = :id",Teacher.class)
                .setParameter("id",id)
                .getSingleResult();
    }
}
