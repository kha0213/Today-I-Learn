package lambda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Ex01_Lambda1BasicTest {
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
        /** 구현체 */
        Something something1 = new DoSomething();

        /** 익명클래스 */
        Something something2 = new Something() {
            @Override
            public void doSomething() {
                System.out.println("익명클래스");
            }
        };

        /** 람다 */
        Something something3 = () -> System.out.println("람다!! 만세");

        something1.doSomething();
        something2.doSomething();
        something3.doSomething();
    }

}
