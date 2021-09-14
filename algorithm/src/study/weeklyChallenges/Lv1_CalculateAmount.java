package study.weeklyChallenges;

/**
 * 코딩테스트 연습 > 위클리 챌린지 > 1주차_부족한 금액 계산하기
 * https://programmers.co.kr/learn/courses/30/lessons/82612
 *
 * Created by Kim Young Long.
 * Date : 2021-09-14.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv1_CalculateAmount {
    public static void main(String[] args) {
        int price = 0;
        int money = 0;
        int count = 0;

        System.out.println(new Lv1_CalculateAmount().solution(price, money, count));
    }

    public long solution(int price, int money, int count) {
        long answer = (long) count * (count + 1) / 2 * price - money;
        return answer < 0? 0 : answer;
    }
}
