package study.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Ex11286 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Comparator<Integer> absoluteMin = (o1, o2) -> {
            int num1 = Math.abs(o1);
            int num2 = Math.abs(o2);
            return num1 == num2 ? Integer.compare(o1, o2) : Integer.compare(num1, num2);
        };
        Queue<Integer> queue = new PriorityQueue(absoluteMin);
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
