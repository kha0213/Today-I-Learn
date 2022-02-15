package com.study.querydsl.controller;

import com.study.querydsl.repository.MemberJpaRepository;
import com.study.querydsl.repository.MemberRepository;
import com.study.querydsl.repository.MemberSearchCond;
import com.study.querydsl.repository.MemberTeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCond searchCond) {
        return memberJpaRepository.search(searchCond);
    }

    @GetMapping("/v2/members")
    public Page<MemberTeamDto> searchMemberV2(MemberSearchCond searchCond, Pageable pageable) {
        return memberRepository.searchPageSimple(searchCond, pageable);
    }

    @GetMapping("/v3/members")
    public Page<MemberTeamDto> searchMemberV3(MemberSearchCond searchCond, Pageable pageable) {
        return memberRepository.searchPageComplex(searchCond, pageable);
    }
}
