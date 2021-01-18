package study.codeChallenges1;

import java.util.ArrayList;
import java.util.List;

/**
 * 2021-01-18
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 2018 KAKAO BLIND RECRUITMENT > 다트 게임
 * https://programmers.co.kr/learn/courses/30/lessons/17682
 */
public class Lv1_DartGame {
    public static void main(String[] args) {
        System.out.println((int)'1');
    }

    public int solution(String dartResult) {
        DartOneShot[] shot = null;
        int answer = 0;
        return answer;
    }

    public List<DartOneShot> playgame(String dartResult){
        List<DartOneShot> shot = new ArrayList<>();
        char[] tempArr = dartResult.toCharArray();

        int score = 0;
        char squared = 'x';
        char option = 'x';

        for(char c : tempArr) {
            if(c == 'S' || c == 'D' || c == 'T'){

            }
        }


        return shot;
    }

    class DartOneShot {
        private int score;
        private char squared;
        private char option;

        public DartOneShot(int score, char squared, char option) {
            this.score = score;
            this.squared = squared;
            this.option = option;
        }

        public int getScore() {
            return score;
        }

        public char getSquared() {
            return squared;
        }

        public char isOption() {
            return option;
        }
    }
}
