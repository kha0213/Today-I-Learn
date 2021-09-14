package study.weeklyChallenges;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 코딩테스트 연습 > 위클리 챌린지 > 2주차_상호평가
 * https://programmers.co.kr/learn/courses/30/lessons/83201
 *
 * Created by Kim Young Long.
 * Date : 2021-09-14.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv2_MutualEvaluation {
    public static void main(String[] args) {
        int[][] scores = new int[][]{{100,90,98,88,65},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65},{24,90,94,75,65}};
        System.out.println(new Lv2_MutualEvaluation().solution(scores));
    }

    public String solution(int[][] scores) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scores.length; i++) {
            sb.append(getGrade(calAvg(scores, i)));
        }
        return sb.toString();
    }

    private int calAvg(int[][] allScores, int idx) {
        int result = 0;
        int myScore = allScores[idx][idx];
        int[] scores = Arrays.stream(allScores).mapToInt(allScore -> allScore[idx])
                .sorted()
                .toArray();
        if ((myScore == scores[0] && myScore != scores[1])
                || myScore == scores[scores.length - 1] && myScore != scores[scores.length - 2]) {
            result = (Arrays.stream(scores).sum() - myScore) / (scores.length - 1);
        } else {
            result = Arrays.stream(scores).sum() / scores.length;
        }
        return result;
    }

    private String getGrade(int score) {
        String result = "";
        switch (score / 10) {
            case 10:
            case 9: result = "A"; break;
            case 8: result = "B"; break;
            case 7: result = "C"; break;
            case 6:
            case 5: result = "D"; break;
            default: result = "F";
        }
        return result;
    }
}
