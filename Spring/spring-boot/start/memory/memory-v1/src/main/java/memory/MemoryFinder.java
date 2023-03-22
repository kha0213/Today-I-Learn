package memory;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemoryFinder {
    public Memory get() {
        Runtime runtime = Runtime.getRuntime();
        long max = runtime.maxMemory();
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long used = total - free;

        log.info("max = {}, total = {}, free = {}, used = {}", max, total, free, used);
        return new Memory(used, max);
    }

    @PostConstruct
    public void init() {
        log.info("MemoryFinder bean created: {}", this);
    }
}
