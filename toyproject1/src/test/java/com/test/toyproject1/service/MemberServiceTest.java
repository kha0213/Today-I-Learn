package com.test.toyproject1.service;

import com.test.toyproject1.entity.Member;
import com.test.toyproject1.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member("영롱");

        //when
        Long saveId = memberService.join(member);

        //then
        Assertions.assertThat(member)
                .isEqualTo(memberRepository.findOne(saveId));
    }

    @Test
    public void 중복회원예외() throws Exception {
        //given
        Member member1 = new Member("영롱");

        Member member2 = new Member("영롱");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalAccessException.class, () -> memberService.join(member2)); // 예외발생
    }


}