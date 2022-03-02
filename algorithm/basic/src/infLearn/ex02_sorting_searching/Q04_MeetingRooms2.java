package infLearn.ex02_sorting_searching;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q04_MeetingRooms2 {
    public static void main(String[] args){
      Q04_MeetingRooms2 ex = new Q04_MeetingRooms2();
      int[][] inputs = {
                {1, 30},
                {6, 18},
                {7, 19},
                {16,20}

      };
      System.out.println(ex.solution(inputs));
      System.out.println(ex.solution2(inputs));
    }
    
    public int solution(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int maxTime = Arrays.stream(intervals).max(Comparator.comparingInt(a -> a[1])).get()[1];
        int[] time = new int[maxTime];
        for (int[] interval : intervals) {
            for (int i = interval[0]; i < interval[1]; i++) {
                time[i]++;
            }
        }
        return Arrays.stream(time).max().getAsInt();
    }
    //{6,18},
    //{16,20},
    //{1,30}
    public int solution2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int[] interval : intervals) {
            if (queue.isEmpty())
                queue.offer(interval);
            else {
                if (queue.peek()[1] <= interval[0]) {
                    queue.poll();
                }
                queue.offer(interval);
            }
        }
        return queue.size();
    }
}
