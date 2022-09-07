package com.example.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@EnableBatchProcessing
@Configuration
public class BatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("basicJob")
                .start(step1())
                // .validator(validator()) validator 사용
                .incrementer(new RunIdIncrementer()) // 자동 증가하는 ID
                .build();
    }

    private Step step1() {
        return this.stepBuilderFactory.get("step1")
                .tasklet(getTaskletWithParam()).build();
    }

    private static Tasklet getTaskletWithParam() {
        return (contribution, chunkContext) -> {
            log.info("hello contribution : [{}] chunkContext : [{}]", contribution, chunkContext);
            // parameter 주고 넘기기
            String name = (String) chunkContext.getStepContext()
                    .getJobParameters()
                    .get("name");
            log.info("==== name [{}]", name);
            return RepeatStatus.FINISHED;
        };
    }

    // defaultJobParametersValidator 로 사용
    @Bean
    public JobParametersValidator defaultJobParametersValidator() {
        DefaultJobParametersValidator validator = new DefaultJobParametersValidator();
        validator.setRequiredKeys(new String[]{"fileName"});
        validator.setOptionalKeys(new String[]{"name"});
        return validator;
    }

    // custom validator 사용
    @Bean
    public JobParametersValidator customJobParametersValidator() {
        return new ParameterValidator();
    }

    // validator 여러 개 일시 결합
    @Bean
    public CompositeJobParametersValidator validator() {
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();

        DefaultJobParametersValidator defaultJobParametersValidator =
                new DefaultJobParametersValidator(new String[]{"fileName"}, new String[]{"name", "run.id"});
        defaultJobParametersValidator.afterPropertiesSet();

        validator.setValidators(Arrays.asList(new ParameterValidator(), defaultJobParametersValidator));

        return validator;
    }
}
