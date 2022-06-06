package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_2 {
    private final TransactionTemplate template;
    private final MemberRepositoryV3 repository;

    public MemberServiceV3_2(PlatformTransactionManager transactionManager, MemberRepositoryV3 repository) {
        this.template = new TransactionTemplate(transactionManager);
        this.repository = repository;
    }

    /**
     * 트랜잭션 템플릿을 이용한다.
     */
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        template.executeWithoutResult((status) -> {
            try {
                bizLogic(fromId, toId, money);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
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
