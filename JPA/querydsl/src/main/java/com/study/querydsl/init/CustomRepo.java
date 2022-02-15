package com.study.querydsl.init;

import com.study.querydsl.repository.MemberCustomRepository;
import com.study.querydsl.repository.MemberSearchCond;
import com.study.querydsl.repository.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomRepo implements MemberCustomRepository {
    @Override
    public List<MemberTeamDto> search(MemberSearchCond searchCond) {
        return null;
    }

    @Override
    public List<MemberTeamDto> searchByBuilder(MemberSearchCond searchCond) {
        return null;
    }

    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCond searchCond, Pageable pageable) {
        return null;
    }

    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCond searchCond, Pageable pageable) {
        return null;
    }
}
