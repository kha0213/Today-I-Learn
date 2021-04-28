package study.codeChallenges1;

import java.util.HashSet;
import java.util.Set;

/**
 *
 *  프로그래머스 > 로또의 최고 순위와 최저 순위
 *  https://programmers.co.kr/learn/courses/30/lessons/77484
 *
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-04-28
 * Time: 오후 10:00
 */
public class Lv1_Lotto {
    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        System.out.println("solution(lottos,win_nums) = " + solution(lottos,win_nums));
    }

    public static int[] solution(int[] lottos, int[] win_nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : win_nums) {
            set.add(num);
        }

        int result = 0;
        int dontKnow = 0;
        for (int lotto : lottos) {
            if(lotto == 0) dontKnow++;
            else if(set.contains(lotto)) result++;
        }

        System.out.println("result = " + result);
        System.out.println("dontKnow = " + dontKnow);
        int min = result > 1 ? 7-result : 6;
        int max = result+dontKnow > 1 ? 7 - (result+dontKnow) : 6;
        return new int[]{min, max};
    }
}
