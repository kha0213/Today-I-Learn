package study.practice;

import java.util.*;
import java.util.function.ToIntFunction;

/**
 * 2021-01-22
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 연습문제 > 땅따먹기
 * https://programmers.co.kr/learn/courses/30/lessons/12913
 */
public class Lv2_GroundPicking {
    public static void main(String[] args) {
        int[][] a = new int[][]{{4, 3, 2, 1},
                                {2, 2, 2, 1},
                                {6, 6, 6, 4},
                                {8, 7, 6, 5}};



        System.out.println(solution(a));
    }
    static int solution(int[][] land) {

        return 0;
    }
    static int[][] arr;

    public int[] recursion(int[][] land, int x, int[] cumulativeSum) {
        if(x == land.length - 1 ) {
            
        } else {

        }
        return null;
    }

    public int maxInArr(int[] arr4, int exceptIdx){
        List<Integer> arr = new ArrayList<>();
        for(int i=0;i<arr4.length;i++){
            if(i!=exceptIdx) arr.add(arr4[i]);
        }
        return Math.max(Math.max(arr.get(0),arr.get(1)),arr.get(2));
    }
}
class Top2LocationAndSum {
    int fSum;
    int fLoc;
    int sSum;
    int sLoc;
    public Top2LocationAndSum(int fSum, int fLoc, int sSum, int sLoc) {
        this.fSum = fSum;
        this.fLoc = fLoc;
        this.sSum = sSum;
        this.sLoc = sLoc;
    }
}
