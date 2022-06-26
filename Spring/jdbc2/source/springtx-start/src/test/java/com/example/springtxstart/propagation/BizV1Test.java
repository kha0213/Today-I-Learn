package com.example.springtxstart.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
public class BizV1Test {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LogRepository logRepository;

    /**
     * memberService    @Transactional off
     * MemberRepository @Transactional on
     * LogRepository    @Transactional on
     */
    @Test
    void outer_tx_off_success() {
        // given
        String username = "outer_tx_off_success";

        // when
        memberService.joinV1(username);

        // when: 모든 데이터가 정상 저장해야한다.
        assertTrue(memberRepository.findByUsername(username).isPresent());
        assertTrue(logRepository.findByMessage(username).isPresent());
    }
}
