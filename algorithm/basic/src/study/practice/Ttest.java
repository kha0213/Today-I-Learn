package study.practice;

import java.util.List;

public class Ttest {
    public static void main(String[] args) {

    }

    public static int simpleArraySum(List<Integer> ar) {
        // Write your code here
        System.out.println("hello");

        return ar.stream()
                .mapToInt(i -> i)
                .sum();
    }
}
