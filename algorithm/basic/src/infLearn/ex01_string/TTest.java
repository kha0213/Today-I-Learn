package infLearn.ex01_string;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TTest {
    public static void main(String[] args) {
        List<Integer> arr = List.of(1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3);
        arr.stream()
                .sorted(Comparator.comparing(i -> (int) i).reversed())
                .collect(Collectors.toList());
        Map<Integer, Long> map =
                arr.stream()
                        .collect(
                                Collectors.groupingBy((i -> i),
                                        Collectors.reducing(0L,i -> 1L,Long::sum)
                                        )
                        );

        Integer key = map.entrySet()
                .stream()
                .sorted((i, j) -> {
                    if (j.getValue() == i.getValue()) {
                        return i.getKey() - j.getKey();
                    }
                    return (int) (j.getValue() - i.getValue());
                }).findFirst().get().getKey();
        System.out.println("key = " + key);
    }
}
