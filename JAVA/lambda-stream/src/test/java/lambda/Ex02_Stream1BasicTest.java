package lambda;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static lambda.Ex02_Stream1BasicTest.Part.BACKEND;
import static lambda.Ex02_Stream1BasicTest.Part.ITS;
import static org.junit.jupiter.api.Assertions.*;

public class Ex02_Stream1BasicTest {

    @Test
    @DisplayName("Stream 생성 연산")
    void streamInit() {
        Stream<String> stream1 = Stream.of("a1", "a2", "b1", "b2", "c2", "c1");

        List<String> list =
                List.of("a1", "a2", "b1", "b2", "c2", "c1");
        Stream<String> stream2 = list.stream().parallel();

        String[] array = new String[]{"a1", "a2", "b1", "b2", "c2", "c1"};
        Stream<String> stream3 = Arrays.stream(array);

        int[] intArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        IntStream intStream = IntStream.of(intArray);
    }

    @Data
    static class Member {
        private final int id;
        private final String name;
        private final int age;
        private final Part part;
    }

    enum Part {
        BACKEND, ITS
    }
    @Test
    @DisplayName("Stream 중단 연산")
    void streamMiddle() {
        List<Member> list = List.of(
                new Member(1,"영롱", 20, BACKEND),
                new Member(2,"성준", 40, BACKEND),
                new Member(3,"지원", 22, BACKEND),
                new Member(4,"수빈", 22, BACKEND),
                new Member(5,"희원", 22, ITS),
                new Member(6,"영호", 7, ITS)
        );

        List<Integer> collect = list.stream()
                .filter(member -> member.getPart() == ITS)
                .sorted(Comparator.comparingInt(Member::getAge).thenComparing(Member::getName))
                .map(member -> member.age)
                .collect(toList());
        System.out.println("collect = " + collect);
    }

    @Test
    @DisplayName("Stream 종단 연산")
    void streamLast() {
        List<Member> list = List.of(
                new Member(1,"영롱", 20, BACKEND),
                new Member(2,"용석", 111, BACKEND),
                new Member(3,"성준", 40, BACKEND),
                new Member(4,"수빈", 22, BACKEND),
                new Member(5,"지원", 29, BACKEND),
                new Member(6,"희원", 30, ITS),
                new Member(7,"영호", 7, ITS)
        );

        boolean match = list.stream()
                .allMatch(member -> member.age > 10);
        assertFalse(match);

        boolean match2 = list.stream()
                .noneMatch(member -> member.age < 100);
        assertFalse(match2);

        Optional<Member> any = list.stream()
                .findAny();
        boolean present = any.isPresent();
        assertTrue(present);

        /** Int Stream은 count, sum, min, average, max 등의 정보를 알 수 있다. */
        IntSummaryStatistics intSummaryStatistics = list.stream()
                .filter(member -> member.part == ITS)
                .mapToInt(member -> member.age)
                .summaryStatistics();
        System.out.println("intSummaryStatistics = " + intSummaryStatistics);
    }

    @Test
    @DisplayName("collect")
    void collect() {
        String[] str = {"영롱","용석","지원","희원","영호","수빈"};
        String collect = Arrays.stream(str)
                .collect(Collectors.joining(","));
        String join = String.join(",", str);

        assertEquals(collect,join);
    }
    @Test
    @DisplayName("flatMap")
    void flatMap() {
        String[][] arrays = {
            {"영롱","지원"},
            {"희원","영호"}
        };

        
        List<String> list = Arrays.stream(arrays)
                .flatMap(Arrays::stream)
                .collect(toList());
        assertEquals(list, List.of("영롱","지원","희원","영호"));
    }

    @Test
    @DisplayName("reduce")
    void reduce() {
        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);

        Optional<Integer> reduce = list.stream()
                .reduce((num1, num2) -> num1 + num2);
        int sum = reduce.get();

        int sum1 = list.stream()
                .mapToInt(i -> i)
                .sum();
        assertEquals(sum, 55);
        assertEquals(sum, sum1);
    }
}
