package study.stackQueue;

import java.util.*;


/**
 *
 * [프로그래머스 lv2 더 맵게]
 * https://programmers.co.kr/learn/courses/30/lessons/42626
 *
 * Created by Kim Young Long.
 * Date : 2021-04-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv2_moreHot {

    public static void main(String[] args) {
        int[] scoville = {1,2,3,9,10,12};
        int K = 7;
        System.out.println(solution(scoville,K));
    }


    public static int solution(int[] scoville, int K) {
        Queue<Integer> queue = new PriorityQueue<>((o1,o2)->o1-o2);
        for (int i : scoville) {
            queue.offer(i);
        }

        int answer = 0;

        while(queue.size() > 1) {
            int poll = queue.poll();
            if (poll >= K) break;
            queue.offer(poll + queue.poll() * 2);
            answer++;
        }

        if(queue.peek() < K) return -1;

        return answer;
    }
}
