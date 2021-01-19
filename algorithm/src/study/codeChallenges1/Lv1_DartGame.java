package study.codeChallenges1;

/**
 * 2021-01-19
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 2018 KAKAO BLIND RECRUITMENT > 다트 게임
 * https://programmers.co.kr/learn/courses/30/lessons/17682
 */
public class Lv1_DartGame {
    public static void main(String[] args) {
        /* 접근방식
         1. 점수 구분 (객체를 통해)
        2. 보너스 계산*/


        System.out.println(solution("1S2D*3T"));

    }

    public static int solution(String dartResult) {
        return DartOneShot.playGame(dartResult);
    }

    static class DartOneShot {
        private final int score;
        private final char squared;
        private final char option;  // 없으면 x 이다.

        private DartOneShot(int score, char squared, char option) {
            this.score = score;
            this.squared = squared;
            this.option = option;
        }

        public static int playGame(String dartResult){
            DartOneShot[] result = new DartOneShot[3];
            int shotNumber = 0;
            for(int i=0; i<dartResult.length(); i++){
                if(dartResult.charAt(i) == 'S' || dartResult.charAt(i) == 'D' || dartResult.charAt(i) == 'T') {
                    char option = 'x';
                    if(i < dartResult.length()-1 && (dartResult.charAt(i+1) == '*' || dartResult.charAt(i+1) == '#')){
                        option = dartResult.charAt(i+1);
                    }
                    int preNum = (int)(dartResult.charAt(i-1)-'0');
                    if(preNum == 0 && i >= 2 && dartResult.charAt(i-2) == '1') {
                        preNum = 10;
                    }
                    result[shotNumber++] = new DartOneShot(preNum,dartResult.charAt(i),option);
                }
            }
            return dartScore(result);
        }

        private static int dartScore(DartOneShot[] result) {
            int[] shot = new int[]{scoreSquared(result[0]),scoreSquared(result[1]),scoreSquared(result[2])};

            for(int i=0; i<result.length;i++){
                if (result[i].isOption() == '#') {
                    shot[i] *= -1;
                } else if (result[i].isOption() == '*') {
                    shot[i] *= 2;
                    if(i>0){
                        shot[i-1] *= 2;
                    }
                }
            }

            return shot[0] + shot[1] + shot[2];
        }

        private static int scoreSquared(DartOneShot dart) {
            if(dart.getSquared() == 'S'){
                return dart.getScore();
            }else if (dart.getSquared() == 'D'){
                return dart.getScore() * dart.getScore();
            }
            return dart.getScore() * dart.getScore() * dart.getScore();
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
