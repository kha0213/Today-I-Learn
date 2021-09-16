package study.weeklyChallenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * 코딩테스트 연습 > 위클리 챌린지 > 7주차
 * https://programmers.co.kr/learn/courses/30/lessons/86048
 *
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-09-14
 * Time: 오후 10:03
 */
public class Lv2_EnterOut {
    public static void main(String[] args) {
        int[] enter = {1, 3, 2};
        int[] leave = {1, 2, 3};
        System.out.println(Arrays.toString(new Lv2_EnterOut().solution(enter, leave)));
    }

    private int[] solution(int[] enter, int[] leave) {
        int[] result = new int[enter.length];

        for (int i = 0; i < enter.length; i++) {
            int person = enter[i];
            boolean afterBeforeLeave = false;
            int firstOutIdx = 0;
            int leaveIdx = 0;
            List<Integer> afterEnterPeople = IntStream.range(i+1,enter.length).boxed().collect(Collectors.toList());
            for (int j = 0; j < leave.length; j++) {
                if(!afterBeforeLeave && afterEnterPeople.contains(leave[j])) {
                    afterBeforeLeave = true;
                    firstOutIdx = j;
                }
                if (leave[j] == person) {
                    leaveIdx = j;
                    break;
                }
            }
            List<Integer> beforeLeavePeople = IntStream.range(0,leaveIdx).boxed().collect(Collectors.toList());
            for (int k = enter.length - 1; k >= 0; k--) {
                if(beforeLeavePeople.contains(enter[k])) {
                    result[person - 1] = Math.max(firstOutIdx - i, k - leaveIdx);
                }
            }
        }
        return result;
    }

}
