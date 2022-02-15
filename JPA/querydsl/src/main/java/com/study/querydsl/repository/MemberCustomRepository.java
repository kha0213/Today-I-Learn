package com.study.querydsl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberCustomRepository {
    List<MemberTeamDto> search(MemberSearchCond searchCond);

    List<MemberTeamDto> searchByBuilder(MemberSearchCond searchCond);

    Page<MemberTeamDto> searchPageSimple(MemberSearchCond searchCond, Pageable pageable);

    Page<MemberTeamDto> searchPageComplex(MemberSearchCond searchCond, Pageable pageable);
}
