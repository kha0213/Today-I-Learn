package com.study.querydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydsl.entity.Member;
import com.study.querydsl.entity.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static com.study.querydsl.util.TestUtil.print;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;


    @Test
    @DisplayName("basic테스트")
    public void basic_test() throws Exception {
        // given
        Member member = new Member("test1", 32);

        // when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).get();

        print(member);

        List<Member> findMembers = memberRepository.findAll();

        List<Member> findByNameMembers = memberRepository.findByUsername("test1");
        // then
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember).isSameAs(member);

        assertThat(findMembers).containsExactly(member);
        assertThat(findByNameMembers).containsExactly(member);

    }

    private void add4Member() {
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

    @Test
    @DisplayName("searchByBuilder, search 테스트")
    public void searchByBuilder_search() throws Exception {
        // given
        add4Member();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        MemberSearchCond searchCond = new MemberSearchCond();
        searchCond.setMemberName("member");
        searchCond.setAgeLoe(35);
        // when
        List<MemberTeamDto> resultBySearchBuilder = memberRepository.searchByBuilder(searchCond);
        List<MemberTeamDto> resultBySearch = memberRepository.search(searchCond);
        System.out.println("=========================");
        print(resultBySearchBuilder);
        System.out.println("=========================");
        print(resultBySearch);
        System.out.println("=========================");

        // then
        assertThat(resultBySearch).containsAll((resultBySearchBuilder));
    }

    @Test
    @DisplayName("page simple 테스트")
    @Commit
    public void pageSimple() throws Exception {
        // given
        add100Member();

        MemberSearchCond searchCond = new MemberSearchCond();

        Pageable pageable = PageRequest.of(1, 10);

        // when
        Page<MemberTeamDto> results = memberRepository.searchPageSimple(searchCond, pageable);

        // then
        assertThat(results.getTotalPages()).isEqualTo(10);
        assertThat(results.getTotalElements()).isEqualTo(100);
        assertThat(results.getContent()).extracting("memberName").contains("member10","member19");
    }

    @Test
    @DisplayName("page complex 테스트")
    @Commit
    public void pageComplex() throws Exception {
        // given
        add100Member();

        MemberSearchCond searchCond = new MemberSearchCond();

        Pageable pageable = PageRequest.of(1, 10);

        // when
        Page<MemberTeamDto> results = memberRepository.searchPageComplex(searchCond, pageable);

        // then
        assertThat(results.getTotalPages()).isEqualTo(10);
        assertThat(results.getTotalElements()).isEqualTo(100);
        assertThat(results.getContent()).extracting("memberName").contains("member10","member19");
    }

    @Test
    @DisplayName("page complex count query 안나가는 테스트")
    @Commit
    public void pageComplex_no_countQuery() throws Exception {
        // given
        add4Member();

        MemberSearchCond searchCond = new MemberSearchCond();

        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<MemberTeamDto> results = memberRepository.searchPageComplex(searchCond, pageable);

        // then
        assertThat(results.getTotalPages()).isEqualTo(1);
        assertThat(results.getTotalElements()).isEqualTo(4);
    }

    private void add100Member() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        for (int i = 0; i < 100; i++) {
            Team selectTeam = i % 2 == 0 ? teamA : teamB;
            em.persist(new Member("member" + i, i, selectTeam));
        }
    }
}