package study.practice;

/**
 * 2021-01-24
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 연습문제 > 124 나라의 숫자
 * https://programmers.co.kr/learn/courses/30/lessons/12899
 */
public class Lv2_124WorldNumber {
    public String solution(int n) {
        StringBuilder world124 = new StringBuilder();
        while(n > 0) {
            int temp = n % 3;
            n /= 3;
            switch (temp) {
                case 0: world124.append(4); n--; break;
                case 1: world124.append(1); break;
                case 2: world124.append(2);
            }
        }
        return world124.reverse().toString();
    }
}
