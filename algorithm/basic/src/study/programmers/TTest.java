package study.programmers;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.util.Comparator.reverseOrder;

public class TTest {

    public static void main(String[] args) {
        List<Integer> list = List.of(100,4,3,2,1);

        System.out.println(Collections.binarySearch(list, 0, reverseOrder())); // 6
        System.out.println(Collections.binarySearch(list, 1, reverseOrder())); // 5
        System.out.println(Collections.binarySearch(list, 3, reverseOrder())); // 3
        System.out.println(Collections.binarySearch(list, 5, reverseOrder())); //  2
        System.out.println(Collections.binarySearch(list, 99, reverseOrder())); // 2
        System.out.println(Collections.binarySearch(list, 101, reverseOrder()));  // 1
    }

}
