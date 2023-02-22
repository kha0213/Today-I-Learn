package lambda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Ex01_Lambda4RunnableTest {
    int a = 0;

    @Test
    @DisplayName("람다로 Runnable 해봐")
    void runnable() {
        Runnable runnable = () -> {
            a++;
            System.out.println("a = " + a);
        };
        runnable.run();



    }

    @FunctionalInterface
    interface MyFun<T,U,R,V> {
        V fun(T ints, U u, R r);

    }

    void funn() {
        Function<Integer, String> funs = integer -> String.valueOf(integer);
        funs.apply(1);
    }


}
