package hello.proxy.pureProxy.decorator;

import hello.proxy.pureProxy.decorator.code.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * 2021-11-22
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Slf4j
public class DecoratorPatternClient {
    private Component component;
    public DecoratorPatternClient(Component component) {
        this.component = component;
    }
    public void execute() {
        String result = component.operation();
        log.info("result={}", result);
    }
}
