package infLearn.ex04_TwoPointer;

import java.util.*;
import java.util.stream.Collectors;

public class Q01_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args){
        String input = "abcabcd";
        System.out.println("Expect Value abcd : "+solution2(input));
        System.out.println("Expect Value 4 : "+solution(input));
        String input2 = "pwwkea";
        System.out.println("Expect Value wkea : "+solution2(input2));
        System.out.println("Expect Value 4 : "+solution(input2));
        String input3 = "aaaaaa";
        System.out.println("Expect Value a : "+solution2(input3));
        System.out.println("Expect Value 1 : "+solution(input3));
        String input4 = "";
        System.out.println("Expect Value \"\" : "+solution2(input4));
        System.out.println("Expect Value 0 : "+solution(input4));
    }
    
    public static int solution(String input) {
        int result = 0;
        Set<Character> set = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (set.contains(c)) {
                result = Math.max(result, set.size());
                set.clear();
                set.add(c);
            } else {
                set.add(c);
            }
        }
        return Math.max(result, set.size());
    }

    // 문자열 리턴해볼까?
    public static String solution2(String input) {
        List<Character> result = new ArrayList<>();
        Set<Character> set = new LinkedHashSet<>();
        for (char c : input.toCharArray()) {
            if (set.contains(c)) {
                if (result.size() <= set.size()) {
                    result.clear();
                    result.addAll(set);
                }
                set.clear();
                set.add(c);
            } else {
                set.add(c);
            }
        }
        if (result.size() <= set.size()) {
            result.clear();
            result.addAll(set);
        }
        return result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
