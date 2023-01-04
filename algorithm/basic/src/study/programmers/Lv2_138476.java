package study.programmers;

import java.util.*;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/138476">...</a>
 */
public class Lv2_138476 {

    public static void main(String[] args) {
        /**
         * 6	[1, 3, 2, 5, 4, 5, 2, 3]	3
         * 4	[1, 3, 2, 5, 4, 5, 2, 3]	2
         * 2	[1, 1, 1, 1, 2, 2, 2, 3]	1
         */
        int[] tangerine = {1, 3, 2, 5, 4, 5, 2, 3};
        //3
        System.out.println(new Lv2_138476().solution(6,tangerine));
    }

    public int solution(int k, int[] tangerine) {
        /*Map<Integer, Integer> map = new <>();
        for (int tan : tangerine) {
            map.put(tan, map.getOrDefault(tan, 0) + 1);
        }*/
        int answer = 0;
        return answer;
    }
}
