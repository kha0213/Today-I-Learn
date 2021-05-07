package study.stackQueue;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 *  [프로그래머스 lv3 이중우선순위큐]
 *  https://programmers.co.kr/learn/courses/30/lessons/42628?language=java
 *
 * Created by Kim Young Long.
 * Date : 2021-04-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv3_DoublePriorityQueue {
    public static void main(String[] args) {
        String[] operation = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println("solution(operation) = " + Arrays.toString(solution(operation)));
    }

    public static int[] solution(String[] operations) {
        Queue<Integer> resultQueue = new LinkedList<>();

        for (String s : operations) {
            switch (s.charAt(0)) {
                case 'I' :
                    resultQueue.add(Integer.parseInt(s.substring(2)));
                    break;
                case 'D' :
                    if(s.charAt(s.length()-2) != '-') {
                        Queue<Integer> descQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
                        descQueue.addAll(resultQueue);
                        descQueue.poll();
                        resultQueue = descQueue;
                    } else {
                        Queue<Integer> ascQueue = new PriorityQueue<>((o1, o2) -> o1 - o2);
                        ascQueue.addAll(resultQueue);
                        ascQueue.poll();
                        resultQueue = ascQueue;
                    }
            }
        }
        if(resultQueue.isEmpty()) return new int[]{0,0};
        List<Integer> collect = resultQueue.stream().sorted().collect(Collectors.toList());
        return new int[]{collect.get(collect.size()-1),collect.get(0)};
    }
}
