package study.weeklyChallenges;

import java.util.Arrays;

/**
 * 코딩테스트 연습 > 위클리 챌린지 > 6주차_복서 정렬하기
 * https://programmers.co.kr/learn/courses/30/lessons/85002
 *
 * Created by Kim Young Long.
 * Date : 2021-09-16.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv1_BoxerSort {
    public static void main(String[] args){
        int[] weights = {50,82,75,120};
        String[] head2head = {"NLWL","WNLL","LWNW","WWLN"};
        System.out.println(Arrays.toString(new Lv1_BoxerSort().solution(weights, head2head)));
    }

    public int[] solution(int[] weights, String[] head2head) {
        Boxer[] boxers = new Boxer[weights.length];
        for (int i = 0; i < weights.length; i++) {
            boxers[i] = new Boxer(i,weights,head2head);
        }
        Arrays.sort(boxers);
        return Arrays.stream(boxers).mapToInt(b -> b.no).toArray();
    }

    static class Boxer implements Comparable<Boxer> {
        int no;
        int weight;
        double winRate;
        int heavyWeightWinCount = 0;

        public Boxer(int no, int[] weights, String[] head2head) {
            this.no = no + 1;
            this.weight = weights[no];
            int winCount = 0;
            int matchCount = 0;
            char[] info = head2head[no].toCharArray();
            for (int i = 0; i < info.length; i++) {
                if(info[i] != 'N'){
                    matchCount++;
                }
                if(info[i] == 'W'){
                    winCount++;
                    if(weights[i] > weight) {
                        heavyWeightWinCount++;
                    }
                }
            }
            winRate = matchCount==0?0:(double) winCount/matchCount;
        }

        @Override
        public int compareTo(Boxer o) {
            if(winRate != o.winRate) return Double.compare(o.winRate, winRate);
            else if(heavyWeightWinCount != o.heavyWeightWinCount) return Integer.compare(o.heavyWeightWinCount, heavyWeightWinCount);
            else if(weight != o.weight) return Integer.compare(o.weight,weight);
            return Integer.compare(no,o.no);
        }
    }
}
