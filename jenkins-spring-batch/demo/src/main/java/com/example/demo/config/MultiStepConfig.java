package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MultiStepConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job exambleJob() {
        return jobBuilderFactory.get("exampleJob")
                .listener(new JobLoggerListener())
                .start(startStep())
                .next(nextStep())
                .next(lastStep())
                .build();
    }

    @Bean
    public Step lastStep() {
        return stepBuilderFactory.get("lastStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("MultiStepConfig.nextStep : {}", LocalDateTime.now());
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step nextStep() {
        return stepBuilderFactory.get("nextStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("MultiStepConfig.nextStep : {}", LocalDateTime.now());
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step startStep() {
        return stepBuilderFactory.get("startStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("MultiStepConfig.startStep : {}", LocalDateTime.now());
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
