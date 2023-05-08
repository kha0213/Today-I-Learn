package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobLoggerListener implements org.springframework.batch.core.JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("JOB 수행 전 {}", jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("JOB 수행 완료 {}", jobExecution);

        if (jobExecution.getStatus().equals(BatchStatus.COMPLETED)) {
            log.info("성공 슬랙 API");
        } else {
            log.info("싪패 슬랙 API");
        }
    }
}
