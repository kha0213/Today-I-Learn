package study.queue;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 최소 힙
 * https://www.acmicpc.net/problem/1927
 */
public class EX1927 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Queue<Integer> queue = new PriorityQueue<>(Integer::compare);
        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            if (num == 0) {
                if (queue.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(queue.poll());
                }
            } else {
                queue.offer(num);
            }
        }
    }
}
