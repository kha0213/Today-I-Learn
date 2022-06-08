package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * 예외 누수 문제해결
 * MemberRepository의 인터페이스에만 의존
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV4 {
    private final MemberRepository repository;

    /**
     * 스프링의 트랜잭션 기능 이용한다.
     */
    @Transactional
    public void accountTransfer(String fromId, String toId, int money) {
        bizLogic(fromId, toId, money);
    }

    private void bizLogic(String fromId, String toId, int money) {
        log.info("from={}, to={}, money={}", fromId, toId, money);

        Member fromMember = repository.findById(fromId);
        Member toMember = repository.findById(toId);

        repository.update(fromId, fromMember.getMoney() - money);

        validation(toId);

        repository.update(toId, toMember.getMoney() + money);
    }

    private void validation(String toId) {
        if (toId.equals("ex")) {
            throw new IllegalStateException("이체 중 에러 발생");
        }
    }
}
