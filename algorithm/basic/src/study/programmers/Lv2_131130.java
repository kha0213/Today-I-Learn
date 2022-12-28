package study.programmers;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/131130">...</a>
 */
public class Lv2_131130 {

    public static void main(String[] args) {
        int[] cards = {8,6,3,7,2,5,1,4};
        //12
        System.out.println(new Lv2_131130().solution(cards));
    }

    public int solution(int[] cards) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Set<Integer> visited = new HashSet<>();

        for (int i = 1; i <= cards.length; i++) {
            int idx= i;
            int cnt = 0;
            Set<Integer> set = new HashSet<>();
            while (!visited.contains(cards[idx - 1])) {
                cnt++;
                visited.add(cards[idx - 1]);
                idx = cards[idx - 1];
            }
            if (cnt > 0) {
                queue.offer(cnt);
            }
        }


        if(queue.size() == 1) return 0;
        return queue.poll() * queue.poll();
    }
}
