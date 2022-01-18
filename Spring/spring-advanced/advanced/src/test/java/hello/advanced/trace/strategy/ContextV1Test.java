package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.Strategy;
import hello.advanced.trace.strategy.code.StrategyLogic1;
import hello.advanced.trace.strategy.code.StrategyLogic2;
import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-11-08
 * Time: 오후 6:47
 */
@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0() {
        login1();
        login2();
    }

    @Test
    void strategyV1() {
        Strategy strategy1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategy1);
        context1.execute();

        Strategy strategy2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategy2);
        context2.execute();
    }

    @Test
    void strategyV2() {
        Strategy strategy1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        log.info("strategy1={}", strategy1.getClass());
        ContextV1 context1 = new ContextV1(strategy1);
        context1.execute();

        Strategy strategy2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2 실행");
            }
        };
        log.info("strategy2={}", strategy2.getClass());
        ContextV1 context2 = new ContextV1(strategy2);
        context2.execute();
    }

    @Test
    void strategyV3() {
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2 실행");
            }
        });
        context2.execute();
    }

    @Test
    void strategyV4() {
        ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직 1 실행"));
        context1.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 로직 2 실행"));
        context2.execute();
    }

    private void login1() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직 1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void login2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직 2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
