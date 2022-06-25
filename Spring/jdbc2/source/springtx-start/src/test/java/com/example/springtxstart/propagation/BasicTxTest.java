package com.example.springtxstart.propagation;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
public class BasicTxTest {
    @Autowired
    PlatformTransactionManager txManager;

    @TestConfiguration
    static class Config {
        @Bean
        PlatformTransactionManager platformTransactionManager(DataSource ds) {
            return new JdbcTransactionManager(ds);
        }
    }


    @Test
    void commit() {
        log.info("트랜잭션 시작");
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션 커밋 시작");
        txManager.commit(status);
        log.info("트랜잭션 커밋 종료");
    }

    @Test
    void rollback() {
        log.info("트랜잭션 시작");
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션 롤백 시작");
        txManager.rollback(status);
        log.info("트랜잭션 롤백 종료");
    }

    @Test
    void double_commit() {
        log.info("트랜잭션 1 시작");
        TransactionStatus status1 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션 1 커밋 시작");
        txManager.commit(status1);
        log.info("트랜잭션 1 커밋 종료");

        log.info("트랜잭션 2 시작");
        TransactionStatus status2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션 2 커밋 시작");
        txManager.commit(status2);
        log.info("트랜잭션 2 커밋 종료");
    }

    @Test
    void commit_rollback() {
        log.info("트랜잭션 1 시작");
        TransactionStatus status1 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션 1 커밋 시작");
        txManager.commit(status1);
        log.info("트랜잭션 1 커밋 종료");

        log.info("트랜잭션 2 시작");
        TransactionStatus status2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션 2 롤백 시작");
        txManager.rollback(status2);
        log.info("트랜잭션 2 롤백 종료");
    }

    @Test
    void inter_commit_exter_commit() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus external = txManager.getTransaction(new DefaultTransactionAttribute());
        boolean external_isNewTx = external.isNewTransaction();
        log.info("외부 트랜잭션은 새로운 TX인가? isNewTransaction = {}", external_isNewTx);
        log.info("내부 트랜잭션 시작");
        TransactionStatus internal = txManager.getTransaction(new DefaultTransactionAttribute());
        boolean internal_isNewTx = internal.isNewTransaction();
        log.info("내부 트랜잭션은 새로운 TX인가? isNewTransaction = {}", internal_isNewTx);
        log.info("내부 트랜잭션 커밋 시작");
        txManager.commit(internal);
        log.info("내부 트랜잭션 커밋 종료");

        log.info("외부 트랜잭션 커밋 시작");
        txManager.commit(external);
        log.info("외부 트랜잭션 커밋 종료");
    }

    @Test
    void exter_rollback_inter_commit() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus external = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("내부 트랜잭션 시작");
        TransactionStatus internal = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("내부 트랜잭션 커밋");
        txManager.commit(internal);

        log.info("외부 트랜잭션 롤백");
        txManager.rollback(external);
    }

    @Test
    void inter_rollback_exter_commit() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus external = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("내부 트랜잭션 시작");
        TransactionStatus internal = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("내부 트랜잭션 롤백");
        txManager.rollback(internal); // rollback-only 마킹

        log.info("외부 트랜잭션 커밋");
        // 기대하지 않은 결과로 에러나며 롤백됨
        Assertions.assertThatThrownBy(() -> txManager.commit(external))
                .isInstanceOf(UnexpectedRollbackException.class);
    }

    @Test
    void inter_rollback_require_new() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus external = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("내부 트랜잭션 시작");
        DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
        attribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus internal = txManager.getTransaction(attribute);

        log.info("내부 트랜잭션 커밋");
        txManager.commit(internal);

        log.info("외부 트랜잭션 롤백");
        txManager.rollback(external);
    }
}
