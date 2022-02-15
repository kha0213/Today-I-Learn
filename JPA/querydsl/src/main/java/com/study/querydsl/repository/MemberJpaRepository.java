package com.study.querydsl.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydsl.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.study.querydsl.entity.QMember.member;
import static com.study.querydsl.entity.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class MemberJpaRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MemberJpaRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Optional<Member> findById(Long memberId) {
        Member member = em.find(Member.class, memberId);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByUsername(String username) {
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<MemberTeamDto> search(MemberSearchCond searchCond) {
        return queryFactory
                .select(
                        new QMemberTeamDto(
                                member.id.as("memberId"),
                                member.username.as("memberName"),
                                member.age,
                                team.id.as("teamId"),
                                team.name.as("teamName")
                        ))
                .from(member)
                .join(member.team, team)
                .where(
                        containsMemberName(searchCond.getMemberName()),
                        containsTeamName(searchCond.getTeamName()),
                        ageGoe(searchCond.getAgeGoe()),
                        ageLoe(searchCond.getAgeLoe()))
                .fetch();
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression containsTeamName(String teamName) {
        return hasText(teamName)? team.name.contains(teamName) : null;
    }

    private BooleanExpression containsMemberName(String memberName) {
        return hasText(memberName) ? member.username.contains(memberName) : null;
    }

    public List<MemberTeamDto> searchByBuilder(MemberSearchCond searchCond) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (hasText(searchCond.getMemberName())) {
            booleanBuilder.and(member.username.contains(searchCond.getMemberName()));
        }
        if (hasText(searchCond.getTeamName())) {
            booleanBuilder.and(team.name.contains(searchCond.getTeamName()));
        }
        if (searchCond.getAgeGoe() != null) {
            booleanBuilder.and(member.age.goe(searchCond.getAgeGoe()));
        }
        if (searchCond.getAgeLoe() != null) {
            booleanBuilder.and(member.age.loe(searchCond.getAgeLoe()));
        }

        return queryFactory
                .select(
                        new QMemberTeamDto(
                                member.id.as("memberId"),
                                member.username.as("memberName"),
                                member.age,
                                team.id.as("teamId"),
                                team.name.as("teamName")
                        ))
                .from(member)
                .join(member.team, team)
                .where(booleanBuilder)
                .fetch();
    }
}
