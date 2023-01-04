package lambda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Lambda1BasicTest {
    interface Something {
        void doSomething();
    }

    static class DoSomething implements Something {
        @Override
        public void doSomething() {
            System.out.println("구현체 클래스 : DoSomething");
        }
    }

    @Test
    @DisplayName("시작")
    void first() {
        // 첫 번째 익명클래스
        Something something1 = new Something() {
            @Override
            public void doSomething() {
                System.out.println("익명클래스");
            }
        };
        // 두 번째 구현체 (기존에 가장 많이 사용하는 방법)
        Something something2 = new DoSomething();
        Something something3 = () -> System.out.println("람다!! 만세");

        something1.doSomething();
        something2.doSomething();
        something3.doSomething();
    }

}
