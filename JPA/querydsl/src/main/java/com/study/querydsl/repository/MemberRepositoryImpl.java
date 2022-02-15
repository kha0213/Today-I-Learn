package com.study.querydsl.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydsl.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.study.querydsl.entity.QMember.member;
import static com.study.querydsl.entity.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
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

    @Override
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

    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCond searchCond, Pageable pageable) {
        QueryResults<MemberTeamDto> result = queryFactory
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<MemberTeamDto> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCond searchCond, Pageable pageable) {
        List<MemberTeamDto> content = queryFactory
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Member> totalQuery = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(
                        containsMemberName(searchCond.getMemberName()),
                        containsTeamName(searchCond.getTeamName()),
                        ageGoe(searchCond.getAgeGoe()),
                        ageLoe(searchCond.getAgeLoe()));

        // 그냥 넘기는것이 아니라 최적화 가능
        return PageableExecutionUtils.getPage(content, pageable, totalQuery::fetchCount);
        //return new PageImpl<>(content, pageable, total);
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
}
