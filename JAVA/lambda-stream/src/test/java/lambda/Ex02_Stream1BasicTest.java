package lambda;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Ex02_Functional_BasicTest {

    @Test
    @DisplayName("Predicate")
    void Predicate() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        // Predicate
        List<Integer> filterList = list.stream()
                .filter(a -> a % 2 == 0)
                .collect(toList());

        Assertions.assertEquals(filterList, List.of(2,4,6,8));
    }

    @Test
    @DisplayName("Function")
    void Function() {
        List<Customer> list = getCustomer();
        // Predicate
        List<String> nameList = list.stream()
                .map(customer -> customer.getName())
                .collect(toList());

        Assertions.assertEquals(nameList, List.of("영롱","성준","수빈","지원"));
    }

    private List<Customer> getCustomer() {
        return List.of(
                new Customer(1, "영롱"),
                new Customer(2, "성준"),
                new Customer(3, "수빈"),
                new Customer(4, "지원")
        );
    }

    @Data
    static class Customer {
        private final int custcd;
        private final String name;
    }

}
