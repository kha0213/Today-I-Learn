package hello.advanced.trace.callback;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-11-08
 * Time: 오후 10:19
 */
@FunctionalInterface
public interface TraceCallback<T> {
    T call();
}
