package com.example.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

/**
 * job listener 잡의 시작 전과 잡의 실행 이후에 각각 실행할 수 있는 메서드를 제공한다.
 * (애너테이션 기반이 아니려면 JobExecutionListener 클래스를 구현하면 된다.)
 * - 알림 : 스프링 클라우드 태스크는 잡의 시작이나 종료를 다른 시스템에 알리는 메세지 큐 메세지를 생성하는
 * JobExecutionListener를 제공한다
 * - 초기화 : 잡 실행전에 준비해둬야 할 뭔가가 있다면 @BeforeJob을 활용하자.
 * - 정리 : 잡 실행 이후에 파일삭제 or 보관 등 작업을 해야하면 @AfterJob을 활용하자.
 * @BeforeJob
 * @AfterJob
 */
@Slf4j
public class JobLoggerListener {
    @BeforeJob
    public void beforeJob() {
        log.info("==========================================================");
        log.info("=== before Job");
        log.info("==========================================================");
    }

    @AfterJob
    public void afterJob() {
        log.info("==========================================================");
        log.info("=== after Job");
        log.info("==========================================================");
    }
}
