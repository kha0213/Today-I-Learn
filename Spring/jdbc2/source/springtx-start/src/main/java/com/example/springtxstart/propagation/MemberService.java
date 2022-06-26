package com.example.springtxstart.propagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final LogRepository logRepository;

    public void joinV1(String username) {
        Member member = new Member(username);
        Log logMessage = new Log(username);

        log.info("=== memberRepository 시작 ===");
        memberRepository.save(member);
        log.info("=== memberRepository 종료 ===");

        log.info("=== logRepository 시작 ===");
        logRepository.save(logMessage);
        log.info("=== logRepository 종료 ===");
    }    
    
    public void joinV2(String username) {
        Member member = new Member(username);
        Log logMessage = new Log(username);

        log.info("=== memberRepository 시작 ===");
        memberRepository.save(member);
        log.info("=== memberRepository 종료 ===");

        try {
            log.info("=== logRepository 시작 ===");
            logRepository.save(logMessage);
            log.info("=== logRepository 종료 ===");
        } catch (RuntimeException e) {
            log.info("로그 저장 중 예외발생 logMessage = {}" , logMessage.getMessage());
            log.info("정상 로직 실행");
        }
    }
}
