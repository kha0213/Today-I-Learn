package study.weeklyChallenges;

import java.util.*;

/**
 * 
 * 프로그래머스 > 위클리 챌린지 > 8주차_최소직사각형
 * https://programmers.co.kr/learn/courses/30/lessons/86491
 * 
 * Created by Kim Young Long.
 * Date : 2021-09-14.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv1_LeastRectangle {
    public static void main(String[] args){
        int[][] sizes = new int[][]{{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        System.out.println(new Lv1_LeastRectangle().solution(sizes));
    }

    public int solution(int[][] sizes) {
        List<Integer> min = new ArrayList<>(sizes.length);
        List<Integer> max = new ArrayList<>(sizes.length);
        for (int[] size : sizes) {
            if (size[0] > size[1]) {
                max.add(size[0]);
                min.add(size[1]);
            } else {
                max.add(size[1]);
                min.add(size[0]);
            }
        }
        return min.stream().max((o1, o2) -> o1 - o2).get()
                * max.stream().max((o1, o2) -> o1 - o2).get();
    }
    
    
}
