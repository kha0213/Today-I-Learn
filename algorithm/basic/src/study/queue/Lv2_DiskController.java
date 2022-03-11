package study.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42627
 */
public class Lv2_DiskController {
    public static void main(String[] args){
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        System.out.println("expected 9 : " + solution(jobs));
        int[][] jobs2 = {{24, 10}, {28, 39}, {43, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 34}, {15, 2}, {35, 43}, {26, 1}};
        System.out.println("expected 72 : " + solution(jobs2));
        int[][] jobs3 = {{0, 5}, {2, 10}, {10000, 2}};
        System.out.println("expected 6 : " + solution(jobs3));
    }

    public static int solution(int[][] jobs) {
        Arrays.sort(jobs, (a,b)->{
            if(a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));

        int sum = jobs[0][1];
        int endTime = jobs[0][0] + jobs[0][1];

        for (int i = 1; i < jobs.length; i++) {
            if (jobs[i][0] > endTime) {
                endTime = Math.max(endTime, queue.peek()[0]);
                while (!queue.isEmpty()) {
                    int[] poll = queue.poll();
                    sum += endTime - poll[0] + poll[1];
                    endTime += poll[1];
                }
            }
            queue.offer(jobs[i]);
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            endTime = Math.max(endTime, poll[0]);
            sum += endTime - poll[0] + poll[1];
            endTime += poll[1];
        }

        return (int) Math.floor(sum / jobs.length);
    }
}
