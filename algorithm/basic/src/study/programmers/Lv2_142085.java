package study.programmers;

import java.util.PriorityQueue;

public class Lv2_142085 {

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((i,j) -> j - i);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        Integer poll = queue.poll();
        System.out.println("poll = " + poll);
    }

    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((i,j) -> j - i);
        for (int i = 0; i < enemy.length; i++) {
            int num = enemy[i];
            queue.offer(num);

            n -= num;

            if (n < 0) {
                if(k == 0) {
                    return i;
                }
                k--;
                int poll = queue.poll();
                n += poll;
            }

        }
        return enemy.length;
    }
}
