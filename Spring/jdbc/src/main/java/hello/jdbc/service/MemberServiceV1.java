package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceV1 {
    private final MemberRepositoryV1 repository;

    /**
     *
     */
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
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
