package com.study.querydsl.em;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydsl.entity.Member;
import com.study.querydsl.entity.QMember;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-05-29
 * Time: 오후 3:29
 */
@SpringBootTest
@Transactional
public class EmConnectionTest {

    //@Autowired 이것도 가능
    @PersistenceContext
    EntityManager em;

    @Test
    void contextLoads() {
        Member member = new Member("김영롱",33);
        em.persist(member);

        JPQLQueryFactory query = new JPAQueryFactory(em);
        QMember qMember = QMember.member;

        final Member findMember = query
                .selectFrom(qMember)
                .fetchOne();

        Assertions.assertThat(findMember).isEqualTo(member);
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());

    }
}
