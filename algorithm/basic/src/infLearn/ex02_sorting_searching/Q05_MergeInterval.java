package infLearn.ex02_sorting_searching;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q05_MergeInterval {
    public static void main(String[] args){
      Q05_MergeInterval ex = new Q05_MergeInterval();
      int[][] intervals = {{1,7},{2,6},{8,10},{15,18}};
      System.out.println(Arrays.deepToString(ex.solution(intervals)));
    }

    public int[][] solution(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (int[] interval : intervals) {
            if(queue.isEmpty()){
                queue.offer(interval);
            }
            if (queue.peek()[1] >= interval[0]) {
                int[] poll = queue.poll();
                queue.offer(new int[]{poll[0], Math.max(poll[1],interval[1])});
            } else {
                queue.offer(interval);
            }
        }
        return queue.toArray(new int[0][]);

    }
}
