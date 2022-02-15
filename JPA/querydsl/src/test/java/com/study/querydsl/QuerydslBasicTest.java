package com.study.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydsl.entity.Member;
import com.study.querydsl.entity.QMember;
import com.study.querydsl.entity.Team;
import com.study.querydsl.entity.dto.MemberDto;
import com.study.querydsl.entity.dto.QMemberDto;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.study.querydsl.entity.QMember.member;
import static com.study.querydsl.entity.QTeam.team;
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
    public void search2() {

        final List<Member> members = queryFactory
                .selectFrom(member)
                .where(member.username.contains("%member%")
                        , member.age.gt(10), null )
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

        assertThat(members.get(0).getUsername()).isEqualTo("member9");
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
        for (Member member1 : members) {
            System.out.println("member = " + member1);
        }
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
        //when
        final List<Tuple> tuples = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .having(member.age.avg().gt(10))
                .fetch();

        for (Tuple tuple : tuples) {
            System.out.println("team.name = " + tuple.get(team.name));
            System.out.println("age.avg() = " + tuple.get(member.age.avg()));
        }
    }

    @Test
    public void join() throws Exception {
        List<Member> result = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();
        assertThat(result).extracting("username")
                .containsExactly("member0", "member2", "member4", "member6", "member8");
    }

    @Test
    public void theta_join() throws Exception {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        List<Member> result = queryFactory
                .selectFrom(member)
                .join(team)
                .on(member.username.eq(team.name))
                .fetch();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).extracting("username")
                .containsExactly("teamA", "teamB");
    }

    @Test
    @DisplayName("페치조인")
    public void fetch_join() throws Exception {
        // given
        em.flush();
        em.clear();
        // when
        List<Member> result = queryFactory.select(member)
                .from(member)
                .join(member.team, team).fetchJoin()
                .where(team.name.eq("teamA"))
                .fetch();

        // then
        for (Member member1 : result) {
            assertThat(Hibernate.isInitialized(member1.getTeam())).isTrue();
        }
    }

    @Test
    @DisplayName("서브쿼리 - 나이가 가장 많은 회원 조회")
    public void sub_query() throws Exception {
        // given
        QMember subMember = new QMember("sub_member");
        // when
        Member result = queryFactory.selectFrom(member)
                .where(
                        member.age.eq(select(subMember.age.max())
                                .from(subMember))
                )
                .fetchOne();
        // then
        assertThat(result.getAge()).isEqualTo(90);
    }

    @Test
    @DisplayName("서브쿼리 - where 절에")
    public void sub_query2() throws Exception {
        // given
        QMember subMember = new QMember("sub_member");
        // when
        List<Member> result = queryFactory.selectFrom(member)
                .where(
                        member.age.goe(select(subMember.age.avg())
                                .from(subMember))
                )
                .orderBy(member.age.asc())
                .fetch();
        // then
        assertThat(result.size()).isEqualTo(5);
        assertThat(result).extracting("age")
                .containsExactly(50, 60, 70, 80, 90);
    }

    @Test
    @DisplayName("서브쿼리 - select 절에")
    public void sub_query3() throws Exception {
        // given
        // when
        List<Tuple> result = queryFactory
                .select(
                        member.id,
                        member.username,
                        member.age,
                        select(team.name)
                                .from(team)
                                .where(team.eq(member.team)))
                .from(member)
                .where(member.team.name.eq("teamA"))
                .orderBy(member.age.asc())
                .fetch();
        // then
        result.stream().map(tuple -> tuple.get(3, String.class)).forEach(
                tuple -> assertThat(tuple).isEqualTo("teamA")
        );
    }

    @Test
    @DisplayName("Dto로 바로 조회 (jpql)")
    public void findDtoByJpql() throws Exception {
        // when
        List<MemberDto> result = em.createQuery(
                "SELECT " +
                        "   new com.study.querydsl.entity.dto.MemberDto(m.username, m.age, t.name) " +
                        "FROM Member m " +
                        "  left outer join m.team as t", MemberDto.class).getResultList();
        // then
        assertThat(result.get(0).getUsername()).isEqualTo("member0");
        assertThat(result.get(0).getAge()).isEqualTo(0);
        assertThat(result.get(0).getTeamName()).isEqualTo("teamA");

    }

    @Test
    @DisplayName("Dto로 바로 조회 (쿼리프로젝션)")
    public void findDtoByQueryProjection() throws Exception {
        // when
        List<MemberDto> result = queryFactory
                .select(new QMemberDto(member.username, member.age, team.name))
                .from(member)
                .join(member.team, team)
                .fetch();

        // then
        assertThat(result.get(0).getUsername()).isEqualTo("member0");
        assertThat(result.get(0).getAge()).isEqualTo(0);
        assertThat(result.get(0).getTeamName()).isEqualTo("teamA");
    }

    @Test
    @DisplayName("튜플연습1")
    public void tuple1() throws Exception {
        // when
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .fetch();
        // then
        assertThat(result)
                .contains("member0", "member1", "member2", "member3", "member4", "member5");
    }

    @Test
    @DisplayName("벌크 연습1")
    public void bulk1() throws Exception {
        // when
        long execute = queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.gt(20))
                .execute();
        // then
        List<Member> members = queryFactory.selectFrom(member).fetch();

        assertThat(execute).isEqualTo(members.stream().filter(m -> m.getAge() > 20).count());

    }
    
    @Test
    @DisplayName("벌크 add")
    public void bulk_add() throws Exception {
        // when
        long execute = queryFactory
                .update(member)
                .set(member.age, member.age.add(100))
                .where(member.username.eq("member1"))
                .execute();

        List<Member> members = queryFactory.selectFrom(member).fetch();

        // then
        Member findMember = members.stream().filter(m -> m.getUsername().equals("member1")).findAny().get();
        assertThat(findMember.getAge()).isGreaterThan(100);
    }
}
