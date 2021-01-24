package study.codeChallenges1;

import java.util.Arrays;

/**
 * 2021-01-22
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 월간 코드 챌린지 시즌1 > 내적
 * https://programmers.co.kr/learn/courses/30/lessons/70128
 */
public class Lv1_InternalCalculation {
    public int solution(int[] a, int[] b) {
        int result = 0;
        for(int i=0;i<a.length;i++){ result += a[i]*b[i]; }
        return result;
    }
}
