package lambda;

import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.comparingInt;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Ex01_Lambda3BasicTest {


    @Data
    static class Customer {
        private String name;
        private int age;

        public Customer(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    @Test
    @DisplayName("람다는 익명함수를 거의 대체 가능하다. (this나 Exception 등 제외)")
    void lambda_ex() {
        /**
         * 해당 배열을 나이순. 나이 같으면 이름 순으로 정렬해보자!
         */
        Customer[] customers1 = new Customer[]{
                new Customer("지원", 26),
                new Customer("영롱", 22),
                new Customer("성준", 25),
                new Customer("수빈", 26),
        };
        Customer[] customers2 = Arrays.copyOf(customers1, 4);
        Customer[] customers3 = Arrays.copyOf(customers1, 4);

        /**
         * 정렬 이후 원하는 결과 값
         */
        Customer[] result = new Customer[]{
                new Customer("영롱", 22),
                new Customer("성준", 25),
                new Customer("수빈", 26),
                new Customer("지원", 26),
        };

        // 1.8 이전 정렬
        Arrays.sort(customers1, getComparator());

        // 익명함수
        Arrays.sort(customers2, (o1, o2) -> {
            if (o1.age != o2.age) {
                return Integer.compare(o1.age, o2.age);
            }
            return o1.name.compareTo(o2.name);
        });

        // 1.8 이후 정렬
        Arrays.sort(customers3,
                comparingInt(Customer::getAge)
                .thenComparing(Customer::getName));


        assertArrayEquals(customers1,result);
        assertArrayEquals(customers2,result);
        assertArrayEquals(customers3,result);
    }

    /**
     * Customer의 Order을 정하기 위한 Comparator
     */
    private Comparator<Customer> getComparator() {
        Comparator<Customer> comparator = new Comparator<>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1.age != o2.age) {
                    return Integer.compare(o1.age, o2.age);
                }
                return o1.name.compareTo(o2.name);
            }
        };
        return comparator;
    }

}
