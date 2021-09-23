package study.codeChallenges1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv2_Test1 {
    public static void main(String[] args){
        int[] progresses = {93,30,55};
        int[] speeds = {1,30,5};
        System.out.println(Arrays.toString(new Lv2_Test1().solution(progresses, speeds)));
    }

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int idx = 0;

        while (idx < progresses.length) {
            int day = (99 - progresses[idx]) /speeds[idx] + 1;
            for (int i = idx; i < progresses.length; i++) {
                progresses[i] += day * speeds[i];
            }
            int count = 0;
            for (int i = idx; i < progresses.length; i++) {
                if(progresses[i] < 100) {
                    break;
                }
                count++;
            }
            answer.add(count);
            idx += count;
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }


}
