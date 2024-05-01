package study.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StringMatchingInAnArray {
    public static void main(String[] args) {
        String[] words = {"leetcode","et","code"};
        System.out.println(stringMatching(words));
    }

    public static List<String> stringMatching(String[] words) {
        List<String> word = Arrays.stream(words).collect(Collectors.toList());
        return Arrays.stream(words)
                .filter(w -> word.stream().anyMatch(i -> i.contains(w) && !w.equals(i)))
                .collect(Collectors.toList());
    }
}
