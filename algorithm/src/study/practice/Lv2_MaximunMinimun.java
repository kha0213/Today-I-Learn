package study.practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *  프로그래머스 > 연습문제 > 최댓값과 최솟값
 *  https://programmers.co.kr/learn/courses/30/lessons/12939
 *
 * Created by Kim Young Long.
 * Date : 2021-04-27.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv2_MaximunMinimun {
    public static void main(String[] args) {
        String s = "1 2 3 4";
        String s2 = "-1 -2 -3 -4";
        String s3 = "-1 -1";
        System.out.println("solution(s) = " + solution(s));
        System.out.println("s2 = " + solution(s2));
        System.out.println("solution(s3) = " + solution(s3));

    }

    public static String solution(String s) {
        String[] split = s.split(" ");
        List<Integer> ints = Arrays.stream(split).map(Integer::parseInt).sorted().collect(Collectors.toList());
        return ints.get(0)+" "+ints.get(ints.size()-1);
    }
}
