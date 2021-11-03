package hello.advanced.advanced.trace.hellotrace;

import hello.advanced.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-11-03
 * Time: 오후 8:38
 */

class HelloTraceV1Test {

    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        final TraceStatus begin = trace.begin("테스트 시작");
        trace.end(begin);
    }

    @Test
    void begin_ex() {
        HelloTraceV1 trace = new HelloTraceV1();
        final TraceStatus begin = trace.begin("에러용 테스트 시작");
        trace.exception(begin,new IllegalArgumentException("에러발생!!"));
    }

}