package study.hash;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;


import static java.util.stream.Collectors.*;

/**
 * 2021-01-18
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 해시 > 완주하지 못한 선수
 * https://programmers.co.kr/learn/courses/30/lessons/42576
 */
public class Lv1_couldNotFinishPlayer {
    public static void main(String[] args) {

    }

    public String solution(String[] participant, String[] completion) {
        Map<String, Long> completionMap = Arrays.stream(completion).
                collect(groupingBy(Function.identity(),counting()));
        for(String temp : participant) {
            Long clone = completionMap.get(temp);
            if(clone == null || clone == 0L){
                return temp;
            }
            completionMap.put(temp, clone - 1L);
        }
        return participant[participant.length-1];
    }
}
