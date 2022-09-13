package com.example.jpashop.service;

import com.example.jpashop.domain.Member;
import com.example.jpashop.repository.MemberJpaRepository;
import com.example.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @TestConfiguration
    static class TestConfig {
        @Autowired
        EntityManager em;

        @Bean
        MemberRepository memberRepository() {
            return new MemberJpaRepository(em);
        }

        @Bean
        MemberService memberService() {
            return new MemberService(memberRepository());
        }
    }


    @Test
    @DisplayName("회원 가입 성공")
    void join() {
        Member member = new Member();
        member.setName("영롱1");
        Long saveId = memberService.join(member);

        assertThat(member).isEqualTo(memberService.findById(saveId));
    }

    @Test
    @DisplayName("회원 가입 실패 - 중복회원 예외")
    void join_fail() {
        Member member = new Member();
        member.setName("영롱");
        Member member2 = new Member();
        member2.setName("영롱");

        memberService.join(member);
        assertThatThrownBy(() -> memberService.join(member2))
                .as("중복 회원 예외 발생")
                .isInstanceOf(IllegalStateException.class);
    }


    @Test
    void findMembers() {
    }

    @Test
    void findById() {
    }
}