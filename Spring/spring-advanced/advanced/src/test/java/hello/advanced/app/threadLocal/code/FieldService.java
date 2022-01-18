package hello.advanced.app.threadLocal.code;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-11-07
 * Time: 오후 10:07
 */
@Slf4j
public class FieldService {
    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String login(String name) {
        log.info("저장 name={} -> nameStore={}", name, nameStore.get());
        nameStore.set(name);
        sleep(1000);
        log.info("조회 nameStore={}",nameStore.get());
        return nameStore.get();
    }
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
