package study.codeChallenges1;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * 프로그래머스 > 실패율율 * https://programmers.co.kr/learn/courses/30/lessons/42889
 *
 *
 * Created by Kim Young Long.
 * Date : 2021-04-29.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv1_FailureRate {
    public static void main(String[] args) {
        int N1 = 5;
        int[] stages1 = {2, 1, 2, 6, 2, 4, 3, 3};
        // 1 2 2 2 3 3 4 6
        int N2 = 4;
        int[] stages2 = {4,4,4,4,4};

        System.out.println("solution(N1,stages1) = " + solution(N1,stages1));
        System.out.println("solution(N2,stages2) = " + solution(N2,stages2));
    }
    public static int[] solution(int N, int[] stages) {
        Set<Stage> set = new TreeSet<>();
        Arrays.sort(stages);
        int stage = 1;
        int pre = 0, count = 0;
        int people = stages.length;

        for (int i = 0; i < people; i++) {
            if(stages[i] > pre) {
                // stage 실패율 구하기
                for( int j = stage; j < stages[i]; j++) {
                    set.add(new Stage(j, (double) count / people - i + 1));
                }
                pre = stages[i];
                count = 1;
            } else {
                count++;
            }
        }
        // 마지막 넣기
        if(stages[people-1] == N) {
            set.add(new Stage())
        } else {

        }

        int[] answer = {};
        return answer;
    }
    static class Stage implements Comparable<Stage> {
        int stage;
        double failureRate;

        public Stage(int stage, double failureRate) {
            this.stage = stage;
            this.failureRate = failureRate;
        }

        @Override
        public int compareTo(Stage o) {
            if (this.failureRate > o.failureRate) return 1;
            else if (this.failureRate < o.failureRate) return -1;
            return this.stage - o.stage;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Stage stage1 = (Stage) o;
            return stage == stage1.stage && Double.compare(stage1.failureRate, failureRate) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(stage, failureRate);
        }
    }
}
