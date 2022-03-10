package study.queue;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Ex13975 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < 2 * T; i++) {
            String s = sc.nextLine();
            if (i % 2 != 0) {
                System.out.println(solution(s));
            }
        }
    }

    private static int solution(String file) {
        Queue<Integer> queue = new PriorityQueue<>(Integer::compare);
        for (String s : file.split(" ")) {
            queue.offer(Integer.valueOf(s));
        }

        int sum = 0;

        while (queue.size() >= 2) {
            int int1 = queue.poll();
            int int2 = queue.poll();
            int tempSum = int1 + int2;
            sum += tempSum;
            queue.offer(tempSum);
        }

        return sum;
    }
}
