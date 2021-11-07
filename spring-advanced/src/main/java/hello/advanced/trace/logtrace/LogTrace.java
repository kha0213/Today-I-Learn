package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-11-07
 * Time: 오후 9:46
 */
public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
