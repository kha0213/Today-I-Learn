package com.study.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydsl.entity.Member;
import com.study.querydsl.entity.QMember;
import com.study.querydsl.entity.QTeam;
import com.study.querydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.study.querydsl.entity.QMember.member;
import static com.study.querydsl.entity.QTeam.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-05-31
 * Time: 오후 11:40
 */
@SpringBootTest
@Transactional
public class QuerydslBasicTest {
    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        for (int i = 0; i < 10; i++) {
            em.persist(new Member("member"+i,i * 10, i % 2 == 0 ? teamA:teamB));
        }

        em.flush();
        em.clear();
    }

    @Test
    public void startJPQL() {
        //member1을 찾아라.
        String query = "select m from Member as m where m.username = :username";
        final Member findMember = em.createQuery(query, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl() {
        //member1을 찾아라.
        QMember m = member;
        final Member findMember = queryFactory
                .selectFrom(m)
                .where(m.username.eq("member1"))
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    // 검색 조건 쿼리
    @Test
    public void search() {
        QMember member = QMember.member;
        final List<Member> members = queryFactory
                .selectFrom(member)
                .where(member.username.like("%member%")
                        .and(member.age.gt(10)))
                .fetch();
        for (Member m : members) {
            assertThat(m.getAge()).isGreaterThan(10);
        }
    }

    // 검색 조건 쿼리
    @Test
    public void resultFetch () {
        final List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();

        final Member fetchOne = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member2"))
                .fetchOne();

        final Member fetchFirst = queryFactory
                .selectFrom(member)
                .fetchFirst();

        final long fetchCount = queryFactory
                .selectFrom(member)
                .fetchCount();

        final QueryResults<Member> fetchResults = queryFactory
                .selectFrom(member)
                .fetchResults();
        fetchResults.getTotal();
        fetchResults.getResults();
    }

    /**
     * 회원 정렬 순서
     * 1. 회원 나이 내림차순 (desc)
     * 2. 회원 나이 오름차순 (asc)
     * 단 2에서 이름이 null이면 마지막에 출력 (nulls last)
     */
    @Test
    public void sort() {
        // 추가 멤버
        em.persist(new Member(null, 100));
        em.persist(new Member("member9", 100));
        em.persist(new Member("member10", 100));

        final List<Member> members = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc(), member.age.asc().nullsLast())
                .fetch();

        assertThat(members.get(0).getUsername()).isEqualTo("member10");
    }

    /**
     * 페이징
     */
    @Test
    public void paging1() {
        final List<Member> members = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc().nullsLast())
                .offset(2) // 시작이 0부터
                .limit(5)
                .fetch();
        assertThat(members.size()).isEqualTo(5);
        assertThat(members.get(0).getAge()).isEqualTo(70); // 90 80 70
    }

    @Test
    public void paging2() {
        // count쿼리가 무조건 나가기 때문에 성능 고려
        final QueryResults<Member> results = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc().nullsLast())
                .offset(7) // 시작이 0부터
                .limit(5)
                .fetchResults();
        assertThat(results.getTotal()).isEqualTo(10); // count 쿼리 전체 개수
        assertThat(results.getLimit()).isEqualTo(5); // limit 개수
        assertThat(results.getOffset()).isEqualTo(7); // 8번째부터
        assertThat(results.getResults().size()).isEqualTo(3); // 8 9 10 3개
    }

    /**
     * 그룹핑
     */
    @Test
    public void aggregation() {
        em.persist(new Member("testM1",100));
        em.persist(new Member("testM2",100));
        em.persist(new Member(null,100));

        final Tuple tuple = queryFactory
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()
                )
                .from(member)
                .fetchFirst();

        assertThat(tuple.get(member.count())).isEqualTo(13);
        assertThat(tuple.get(member.age.max())).isEqualTo(100);
        assertThat(tuple.get(member.age.min())).isEqualTo(0);

    }

    @Test
    public void group() throws Exception {
        //given


        //when
        queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)


        //then
    }
}
