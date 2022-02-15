package com.study.querydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydsl.entity.Member;
import com.study.querydsl.entity.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static com.study.querydsl.util.TestUtil.print;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    MemberJpaRepository memberJpaRepository;


    @Test
    @DisplayName("basic테스트")
    public void basic_test() throws Exception {
        // given
        Member member = new Member("test1", 32);

        // when
        memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.findById(member.getId()).get();

        print(member);

        List<Member> findMembers = memberJpaRepository.findAll();

        List<Member> findByNameMembers = memberJpaRepository.findByUsername("test1");
        // then
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember).isSameAs(member);

        assertThat(findMembers).containsExactly(member);
        assertThat(findByNameMembers).containsExactly(member);

    }

    @Test
    @DisplayName("searchByBuilder, search 테스트")
    public void searchByBuilder_search() throws Exception {
        // given
        basicAddMember();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        MemberSearchCond searchCond = new MemberSearchCond();
        searchCond.setMemberName("member");
        searchCond.setAgeLoe(35);
        // when
        List<MemberTeamDto> resultBySearchBuilder = memberJpaRepository.searchByBuilder(searchCond);
        List<MemberTeamDto> resultBySearch = memberJpaRepository.search(searchCond);
        System.out.println("=========================");
        print(resultBySearchBuilder);
        System.out.println("=========================");
        print(resultBySearch);
        System.out.println("=========================");

        // then
        assertThat(resultBySearch).containsAll((resultBySearchBuilder));
    }

    private void basicAddMember() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

}