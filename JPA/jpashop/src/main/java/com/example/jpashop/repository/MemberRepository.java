package com.example.jpashop.repository;

import com.example.jpashop.domain.Member;

import java.util.List;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long id);

    List<Member> findAll();

    List<Member> findByName(String name);
}
