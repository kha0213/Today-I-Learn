package study.stackQueue;

import java.util.*;

/**
 *
 *  [프로그래머스 lv3 디스크컨트롤러]
 *  https://programmers.co.kr/learn/courses/30/lessons/42627
 *
 * Created by Kim Young Long.
 * Date : 2021-04-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv3_DiskController {
    public static void main(String[] args) {
        int[][] jobs = new int[][]{{0,3},{1,9},{2,6}};
        System.out.println("solution(jobs) = " + solution(jobs));
    }
    public static int solution(int[][] jobs) {
        List<Interval> intervals = new LinkedList<>();
        for(int[] i : jobs) {
            intervals.add(new Interval(i[0],i[1]));
        }

        intervals.sort(((o1, o2) -> o1.start-o2.start));

        int allTime = 0;

        for (Interval i : intervals) {
            
        }



        int result = 0;
        return result;
    }
    static class Interval{
        int time;
        int start;
        int run;
        Interval(int s, int e){
            this.start = s;
            this.run = e;
        }
    }
}
