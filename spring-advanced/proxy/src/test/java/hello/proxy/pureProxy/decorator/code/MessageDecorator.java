package hello.proxy.pureProxy.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 2021-11-22
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@RequiredArgsConstructor
@Slf4j
public class MessageDecorator implements Component {

    private final Component component;

    @Override
    public String operation() {
        log.info("message Decorator 실행!");
        String result = component.operation();
        String decoResult = "😊👍😃" + result + "😊👍😃";
        log.info("MessageDecorator 꾸미기 적용 전={}, 적용 후={}", result,
                decoResult);
        return decoResult;
    }
}
