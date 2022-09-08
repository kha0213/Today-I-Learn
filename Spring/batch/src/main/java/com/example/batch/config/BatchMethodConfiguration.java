//package com.example.batch.config;
//
//import com.example.batch.external.ExternalService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Slf4j
//@EnableBatchProcessing
//@Configuration
//public class BatchMethodConfiguration {
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job methodInvokingJob() {
//        return jobBuilderFactory.get("methodInvokingJob")
//                .start(methodInvokingStep())
//                .incrementer(new DailyJobTimestamper()) // 자동 증가하는 ID
//                .listener(new JobLoggerListener())
//                .build();
//    }
//
//    @Bean
//    public Step methodInvokingStep() {
//        return stepBuilderFactory.get("methodInvokingStep")
//                .tasklet(methodInvokingTasklet())
//                .build();
//    }
//
//    @Bean
//    public MethodInvokingTaskletAdapter methodInvokingTasklet() {
//        MethodInvokingTaskletAdapter methodInvokingTaskletAdapter
//                = new MethodInvokingTaskletAdapter();
//        methodInvokingTaskletAdapter.setTargetObject(externalService());
//        methodInvokingTaskletAdapter.setTargetMethod("serviceMethod");
//        return methodInvokingTaskletAdapter;
//    }
//
//    @Bean
//    public ExternalService externalService() {
//        return new ExternalService();
//    }
//
//}
