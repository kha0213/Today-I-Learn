package study.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *  [프로그래머스 > 해시 > 위장]
 *  https://programmers.co.kr/learn/courses/30/lessons/42578
 *
 * Created by Kim Young Long.
 * Date : 2021-04-26.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv2_Camouflage {
    public static void main(String[] args) {
        String[][] clothes = {
            {"yellowhat", "headgear"},
            {"bluesunglasses", "eyewear"},
            {"green_turban", "headgear"}
        };

        System.out.println("solution(clothes) = " + solution(clothes));
    }

    public static int solution(String[][] clothes) {
        Map<String, List<String>> map = new HashMap<>();
        for (String[] s : clothes) {
            List<String> arr = map.getOrDefault(s[1], new ArrayList<>());
            arr.add(s[0]);
            map.put(s[1], arr);
        }

        int answer = 1;
        for (List<String> value : map.values()) {
            answer *= value.size() + 1;
        }
        answer--;
        return answer;
    }
}
