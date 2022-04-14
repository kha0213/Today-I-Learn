package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {
    private final DataSource ds;
    private final MemberRepositoryV2 repository;

    /**
     *
     */
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection con = null;
        try {
            con = ds.getConnection();
            con.setAutoCommit(false);

            // 비즈니스 s
            bizLogic(con, fromId, toId, money);
            // 비즈니스 e

            con.commit();
        } catch (Exception e) {
            con.rollback();
            log.error("error", e);
            throw e;
        } finally {
            release(con);
        }
    }

    private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
        log.info("from={}, to={}, money={}", fromId, toId, money);

        Member fromMember = repository.findById(con, fromId);
        Member toMember = repository.findById(con, toId);

        repository.update(con, fromId, fromMember.getMoney() - money);

        validation(toId);

        repository.update(con, toId, toMember.getMoney() + money);
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
