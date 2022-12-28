package study.programmers;

import java.util.Arrays;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42842">...</a>
 */
public class Lv2_42842 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Lv2_42842().solution(24, 24)));
    }

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int area = brown + yellow;
        int width = 3;
        while (true) {
            if (area % width == 0 && yellow % ((area / width) - 2) == 0) {
                answer[0] = Math.max(area / width, width);
                answer[1] = Math.min(area / width, width);
                break;
            }
            width++;
        }
        return answer;
    }
}
