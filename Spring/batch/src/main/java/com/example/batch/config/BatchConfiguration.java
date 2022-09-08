package com.example.batch.config;

import com.example.batch.external.ExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
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
                .next(step2())
                .next(methodInvokingStep())
                .next(methodInvokingStep())
                // .validator(validator()) validator 사용
                //.incrementer(new RunIdIncrementer()) // 자동 증가하는 ID
                .incrementer(new DailyJobTimestamper()) // 자동 증가하는 ID
                .listener(JobListenerFactoryBean.getListener(new JobLoggerListener()))
                .build();
    }

    @Bean
    public Job methodInvokingJob() {
        return jobBuilderFactory.get("methodInvokingJob")
                .start(methodInvokingStep())
                .build();
    }

    private Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    /** 잡의 Execution Context 가져오기 같은 Job에서 실행되는 Step이면 같은 Context리턴 */
                    ExecutionContext jobExecutionContext = chunkContext.getStepContext()
                            .getStepExecution()
                            .getJobExecution()
                            .getExecutionContext();

                    /** 스텝의 Execution Context 가져오기 (Step 마다 생성되는 Context) */
                    ExecutionContext stepExecutionContext = chunkContext.getStepContext()
                            .getStepExecution()
                            .getExecutionContext();
                    log.info("=== step2 job Context: [{}]", jobExecutionContext); //같은 job이면 step1의 context와 동일
                    log.info("=== step2 step Context: [{}]", stepExecutionContext); // 같은 job이어도 step마다 생성되는 context임
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    /** 잡의 Execution Context 가져오기 */
                    ExecutionContext jobExecutionContext = chunkContext.getStepContext()
                            .getStepExecution()
                            .getJobExecution()
                            .getExecutionContext();
                    jobExecutionContext.put("name", "job Execution Context");

                    /** 스텝의 Execution Context 가져오기 (Job과 다름) */
                    ExecutionContext stepExecutionContext = chunkContext.getStepContext()
                            .getStepExecution()
                            .getExecutionContext();
                    stepExecutionContext.put("name2", "step Execution Context");
                    log.info("=== step1 job Context: [{}]", jobExecutionContext);
                    log.info("=== step1 step Context: [{}]", stepExecutionContext);
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Tasklet getTaskletWithParam() {
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
                new DefaultJobParametersValidator(new String[]{"fileName"}, new String[]{"name", "currentDate"});
        defaultJobParametersValidator.afterPropertiesSet();

        validator.setValidators(Arrays.asList(new ParameterValidator(), defaultJobParametersValidator));

        return validator;
    }

    public Step methodInvokingStep() {
        return stepBuilderFactory.get("methodInvokingStep")
                .tasklet(methodInvokingTasklet())
                .build();
    }

    /**
     * 외부 서비스
     */
    @Bean
    public ExternalService externalService() {
        return new ExternalService();
    }

    @Bean
    public MethodInvokingTaskletAdapter methodInvokingTasklet() {
        MethodInvokingTaskletAdapter methodInvokingTaskletAdapter =
                new MethodInvokingTaskletAdapter();

        methodInvokingTaskletAdapter.setTargetObject(externalService());
        methodInvokingTaskletAdapter.setTargetMethod("serviceMethodWithNoParameter");

        return methodInvokingTaskletAdapter;
    }
}
