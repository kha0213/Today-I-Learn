package study.codeChallenges1;

import javax.lang.model.SourceVersion;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * 프로그래머스 > 찾아라 프로그래밍 마에스터 > 폰켓몬
 * https://programmers.co.kr/learn/courses/30/lessons/1845
 *
 * Created by Kim Young Long.
 * Date : 2021-04-27.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv1_PhoneCatMon {
    public static void main(String[] args) {
        int[] nums = {3,1,2,3};
        int[] nums2 = {3,3,3,2,2,4};
        int[] nums3 = {3,3,3,2,2,2};
        System.out.println("solution(nums) = " + solution(nums));
        System.out.println("solution(nums2) = " + solution(nums2));
        System.out.println("solution(nums3) = " + solution(nums3));
    }

    public static int solution(int[] nums) {
        return Math.min(Arrays.stream(nums).boxed().collect(Collectors.toSet()).size(),nums.length/2);
    }
}
