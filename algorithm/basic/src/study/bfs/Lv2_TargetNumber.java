package study.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/43165
 * 프로그래머스 타겟 넘버
 */
public class Lv2_TargetNumber {
    public static void main(String[] args){
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println("expected 5 : " + solution(numbers, target));
        int[] numbers2 = {4, 1, 2, 1};
        int target2 = 4;
        System.out.println("expected 2 : " + solution(numbers2, target2));
    }

    public static int solution(int[] numbers, int target) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(numbers[0]);
        queue.offer(-numbers[0]);
        int index = 1;
        while (index < numbers.length) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                queue.offer(poll + numbers[index]);
                queue.offer(poll - numbers[index]);
            }
            index++;
        }

        return (int) queue.stream().filter(num -> num == target).count();
    }
}
