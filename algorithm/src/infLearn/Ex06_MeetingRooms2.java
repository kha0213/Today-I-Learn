package infLearn;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * Question 6 : [MeetingRooms2]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Ex06_MeetingRooms2 {

    public static void main(String[] args) {
        Interval[] intervals1 = {new Interval(0,30),new Interval(15,20),new Interval(5,10)};
        Interval[] intervals2 = {new Interval(7,10),new Interval(2,4)};

        Interval[] intervals3 = {new Interval(7,10),
                                 new Interval(8,20),
                                 new Interval(3,9),
                                 new Interval(1,8),
                                 new Interval(10,15),
                                 new Interval(19,22),
                                 new Interval(3,30)
                                };

        System.out.println("1번째 : "+solve(intervals1));
        System.out.println("2번째 : "+solve(intervals2));
        System.out.println("3번째 : "+solve(intervals3));
    }

    public static int solve(Interval[] intervals) {
        Arrays.sort(intervals, (o1,o2) -> o1.start - o2.start);

        Queue<Interval> data = new PriorityQueue<>((o1, o2) -> o2.end - o1.end);
        data.addAll(Arrays.asList(intervals));

        int result = 0;
        Interval before = data.poll();

        for (Interval interval : data) {
            if(before == null) {
                before = interval;
            } else if(before.start >= interval.end) {
                before.start = interval.start;
            } else {
                result++;
                before = null;
            }
        }
        if(before!=null) result++;

        return result;
    }


    static class Interval{
        int start;
        int end;
        Interval(){
            this.start = 0;
            this.end =0;
        }
        Interval(int s, int e){
            this.start = s;
            this.end = e;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
