package study.codeChallenges1;

/**
 * 2021-01-22
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 월간 코드 챌린지 시즌1 > 3진법 뒤집기
 * https://programmers.co.kr/learn/courses/30/lessons/68935
 */
public class ThreeBaseReverse {
    public static void main(String[] args) {
        ThreeBaseReverse th = new ThreeBaseReverse();
        System.out.println(th.solution(15485212));

    }

    // Long.toString을 몰라서 3진법 변환했었음.
    public long solution(int n) {
        String sevenBaseNum = Long.toString(n, 3);
        StringBuilder sb = new StringBuilder(sevenBaseNum);
        return Long.parseLong(sb.reverse().toString(),3);
    }

}
