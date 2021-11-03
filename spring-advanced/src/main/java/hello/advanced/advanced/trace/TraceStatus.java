package hello.advanced.advanced.trace;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-11-03
 * Time: 오후 6:10
 */
@Data
@AllArgsConstructor
public class TraceStatus {
    private TraceId traceId;
    private Long startTimeMs;
    private String message;
}
