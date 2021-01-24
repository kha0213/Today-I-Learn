package study.practice;

import java.util.Arrays;
import java.util.TreeSet;

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

    /*public int recursion(int x, int firstIndex, int firstSum, int secondIndex, int secondSum) {
        for(int i=0; i<4; i++){
            if(i != firstIndex) {

            }
            if(i != secondIndex) {

            }
        }
        if (x == arr.length-1){
            return Math.max(firstSum,secondSum);
        } else {
            recursion(x+1);
        }
    }*/
}
class Ground {

}
