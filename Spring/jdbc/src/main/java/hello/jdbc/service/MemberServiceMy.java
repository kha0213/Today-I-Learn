package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryMy;
import hello.jdbc.util.TxManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

/**
 * JDBC 소스 의존 없게 수정
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceMy {
    private final TxManager tx;
    private final MemberRepositoryMy repository;

    /**
     *
     */
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        try {
            tx.begin();

            // 비즈니스 s
            log.info("from={}, to={}, money={}", fromId, toId, money);

            Member fromMember = repository.findById(fromId);
            Member toMember = repository.findById(toId);

            repository.update(fromId, fromMember.getMoney() - money);

            validation(toId);

            repository.update(toId, toMember.getMoney() + money);
            // 비즈니스 e

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.error("error", e);
            throw e;
        }
    }

    private void validation(String toId) {
        if (toId.equals("ex")) {
            throw new IllegalStateException("이체 중 에러 발생");
        }
    }
}
