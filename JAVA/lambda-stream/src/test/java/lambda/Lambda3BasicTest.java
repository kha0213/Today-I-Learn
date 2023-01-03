package lambda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiFunction;

import static java.util.Comparator.comparingInt;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Lambda3BasicTest {


    static class Customer {
        private String name;
        private int age;

        public Customer(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + ":" + age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
            return age == customer.age && Objects.equals(name, customer.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }
    @Test
    @DisplayName("람다는 익명함수를 거의 대체 가능하다. (this나 Exception 등 제외)")
    void param_fun() {
        // Customer을 정렬하기 위한 Comparator을 보자.
        Customer[] customers = new Customer[]{
                new Customer("지원", 26),
                new Customer("영롱", 22),
                new Customer("성준", 25),
                new Customer("수빈", 26),
        };
        // 1.8 이전 정렬
        Arrays.sort(customers, getComparator());

        // 참고 익명함수
        /*
        Arrays.sort(customers, new Comparator<>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1.age != o2.age) {
                    return Integer.compare(o1.age, o2.age);
                }
                return o1.name.compareTo(o2.name);
            }
        });
        */
        
        // 1.8 이후 정렬
        Arrays.sort(customers,
                comparingInt(Customer::getAge)
                .thenComparing(Customer::getName));

        Customer[] result = new Customer[]{
                new Customer("영롱", 22),
                new Customer("성준", 25),
                new Customer("수빈", 26),
                new Customer("지원", 26),
        };
        assertArrayEquals(customers,result);
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
