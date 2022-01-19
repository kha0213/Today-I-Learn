package infLearn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Question 1 : [MeetingRooms]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */

public class Ex01_MeetingRooms1 {

    public static void main(String[] args) {
        Interval[] it = new Interval[2];
        it[0] = new Interval(7,10);
        it[1] = new Interval(2,4);

        System.out.println(solve(it));
    }

    public static boolean solve(Interval[] intervals) {
        List<Interval> collect = Arrays.stream(intervals).sorted((o1, o2) -> o1.start - o2.start).collect(Collectors.toList());
        for (int i=0; i<collect.size() - 1; i++) {
            if(collect.get(i).end > collect.get(i+1).start) {
                return false;
            }

        }
        return true;
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
    }

}


