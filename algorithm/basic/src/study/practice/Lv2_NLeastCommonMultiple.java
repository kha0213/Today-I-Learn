package study.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 *
 *  프로그래머스 > 연습문제 > N개의 최소공배수
 *  https://programmers.co.kr/learn/courses/30/lessons/12953
 *
 * Created by Kim Young Long.
 * Date : 2021-04-27.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv2_NLeastCommonMultiple {
    public static void main(String[] args) {
        int[] arr = {2, 6, 8, 14};
        System.out.println("solution(arr) = " + solution(arr));

    }

    public static int solution(int[] arr) {
        Arrays.sort(arr);
        int max = arr[arr.length-1];
        int answer = max;
        all : while (true) {
            answer += max;
            for (int i = arr.length - 1; i >= 0; i--) {
                if(answer % arr[i] != 0) continue all;
            }
            break;
        }
        return answer;
    }
}
