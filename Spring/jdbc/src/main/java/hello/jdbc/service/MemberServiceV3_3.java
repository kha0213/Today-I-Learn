package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_3 {
    private final MemberRepositoryV3 repository;

    /**
     * 스프링의 트랜잭션 기능 이용한다.
     */
    @Transactional
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        bizLogic(fromId, toId, money);
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        log.info("from={}, to={}, money={}", fromId, toId, money);

        Member fromMember = repository.findById(fromId);
        Member toMember = repository.findById(toId);

        repository.update(fromId, fromMember.getMoney() - money);

        validation(toId);

        repository.update(toId, toMember.getMoney() + money);
    }

    private void release(Connection con) {
        if (con != null) {
            try {
                con.setAutoCommit(true); // 커넥션 풀 고려
                con.close();
            } catch (Exception e) {
                log.error("error: " , e);
            }
        }
    }

    private void validation(String toId) {
        if (toId.equals("ex")) {
            throw new IllegalStateException("이체 중 에러 발생");
        }
    }
}
