package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.FieldLogTrace;
import org.junit.jupiter.api.Test;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-11-07
 * Time: 오후 9:49
 */
class FieldLogTraceTest {
    FieldLogTrace trace = new FieldLogTrace();

    @Test
    void begin_hello() {
        TraceStatus hello1 = trace.begin("hello1");
        TraceStatus hello2 = trace.begin("hello2");
        TraceStatus hello3 = trace.begin("hello3");
        trace.end(hello1);
        trace.end(hello2);
        trace.end(hello3);
    }
}