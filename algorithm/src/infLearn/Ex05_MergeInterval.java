package infLearn;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 *  Question 5 : [MergeInterval]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Ex05_MergeInterval {
    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(15, 18));

        solve(intervals).forEach(System.out::println);
    }

    public static List<Interval> solve(List<Interval> intervals) {
        intervals.sort((o1,o2) -> o1.start - o2.start);

        List<Interval> result = new ArrayList<>();

        Stack<Interval> stack = new Stack<>();
        stack.push(intervals.get(0));

        for(int i = 1; i < intervals.size(); i++) {
            Interval peek = stack.peek();
            if(peek.end > intervals.get(i).start) {
                peek.end = Math.max(peek.end,intervals.get(i).end);
            } else {
                result.add(stack.pop());
                stack.add(intervals.get(i));
            }
        }
        if(!stack.isEmpty()) result.add(stack.pop());


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
