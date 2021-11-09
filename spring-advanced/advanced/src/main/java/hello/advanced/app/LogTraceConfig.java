package hello.advanced.app;

import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.FieldLogTrace;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-11-07
 * Time: 오후 9:53
 */
@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace trace() {
        return new ThreadLocalLogTrace();
    }

    @Bean
    public HelloTraceV1 helloTraceV1() {
        return new HelloTraceV1();
    }
    @Bean
    public HelloTraceV2 helloTraceV2() {
        return new HelloTraceV2();
    }

}
