package com.example.springtxstart.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

    /**
     * memberService    @Transactional on
     * MemberRepository @Transactional on
     * LogRepository    @Transactional on
     */
    @Test
    void outer_tx_on_success() {
        // given
        String username = "outer_tx_off_success";

        // when
        memberService.joinV1(username);

        // when: 모든 데이터가 정상 저장해야한다.
        assertTrue(memberRepository.findByUsername(username).isPresent());
        assertTrue(logRepository.findByMessage(username).isPresent());
    }

    /**
     * memberService    @Transactional on
     * MemberRepository @Transactional on
     * LogRepository    @Transactional on (Exception)
     */
    @Test
    void outer_tx_on_fail() {
        // given
        String username = "로그예외_outer_tx_on_fail";

        // when
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> memberService.joinV2(username))
                .isInstanceOf(UnexpectedRollbackException.class);

        // then: 모든 데이터가 롤백된다
        assertTrue(memberRepository.findByUsername(username).isEmpty());
        assertTrue(logRepository.findByMessage(username).isEmpty());
    }

    /**
     * memberService    @Transactional on
     * MemberRepository @Transactional on
     * LogRepository    @Transactional on (Exception)
     */
    @Test
    void outer_tx_on_fail_require_new() {
        // given
        String username = "로그예외_outer_tx_on_fail_require_new";

        // when
        memberService.joinV2(username);

        // then: 로그만 롤백된다.
        assertTrue(memberRepository.findByUsername(username).isPresent());
        assertTrue(logRepository.findByMessage(username).isEmpty());
    }

    @Test
    @DisplayName("Require_New에 대해 테스트")
    void require_new_test() {
        // given
        String username = "로그예외";

        // when
        assertThrows(Exception.class, () -> memberService.joinV3(username));
        //memberService.joinV3(username);
        // then: 로그만 롤백된다.
        assertTrue(logRepository.findByMessage(username).isPresent());
        //assertTrue(memberRepository.findByUsername(username).isPresent());
    }
}
