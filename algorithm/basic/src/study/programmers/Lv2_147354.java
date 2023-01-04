package study.programmers;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/147354">...</a>
 */
public class Lv2_147354 {
    public static void main(String[] args) {
        // 4
        System.out.println(new Lv2_147354().solution(new int[][]{{2, 2, 6}, {1, 5, 10}, {4, 2, 9}, {3, 8, 3}},
                2, 2, 3));
    }
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (o1, o2) -> {
            int i = col - 1;
            if (o1[i] != o2[i]) {
                return o1[i] - o2[i];
            }
            return o2[0] - o1[0];
        });

        int answer = 0;
        for (int i = row_begin; i <= row_end; i++) {
            int idx = i;
            int sum = IntStream.of(data[idx - 1]).map(num -> num % idx).sum();
            answer ^= sum;
        }
        return answer;
    }
}
