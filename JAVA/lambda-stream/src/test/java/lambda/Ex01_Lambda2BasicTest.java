package lambda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

public class Ex01_Lambda2BasicTest {

    @Test
    @DisplayName("함수도 파라미터로 넘길 수 있다.")
    void param_fun() {
        int cal1 = cal(10, 5, (Integer x, Integer y) -> x + y);
        System.out.println("cal1 : " + cal1);
        int cal2 = cal(10, 5, (Integer x, Integer y) -> x - y);
        System.out.println("cal2 : " + cal2);
        int cal3 = cal(10, 5, (Integer x, Integer y) -> x * y);
        System.out.println("cal3 : " + cal3);
        int cal4 = cal(10, 5, (Integer x, Integer y) -> x / y);
        System.out.println("cal4 : " + cal4);
    }

    /**
     * x, y 두 숫자와 게산식을 받아 계산하는 함수
     */
    private int cal(int x, int y, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(x, y);
    }


}
