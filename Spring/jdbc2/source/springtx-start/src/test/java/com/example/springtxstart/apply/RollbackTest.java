package com.example.springtxstart.apply;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class RollbackTest {
    @Autowired
    BasicService basicService;

    @TestConfiguration
    static class config {
        @Bean
        BasicService basicService() {
            return new BasicService();
        }
    }

    @Test
    @DisplayName("체크드 예외는 커밋")
    void checkedException() {
        assertThatThrownBy(() -> basicService.checkedException())
                .isInstanceOf(MyException.class);
    }

    @Test
    @DisplayName("언체크드 예외는 롤백")
    void uncheckedException() {
        assertThatThrownBy(() -> basicService.uncheckedException())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("롤백For은 롤백")
    void rollbackFor() {
        assertThatThrownBy(() -> basicService.rollbackFor())
                .isInstanceOf(MyException.class);
    }

    @Slf4j
    @RequiredArgsConstructor
    static class BasicService {
        @Transactional
        public void checkedException() throws MyException {
            log.info("checkedException!!");
            throw new MyException();
        }
        @Transactional(rollbackFor = {MyException.class})
        public void rollbackFor() throws MyException {
            log.info("rollbackFor!!");
            throw new MyException();
        }
        @Transactional
        public void uncheckedException() {
            log.info("uncheckedException");
            throw new RuntimeException();
        }

    }
    static class MyException extends Exception {
    }
}
