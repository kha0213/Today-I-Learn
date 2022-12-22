package study.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * k	ranges	                        result
 * 5	[[0,0],[0,-1],[2,-3],[3,-3]]	[33.0,31.5,0.0,-1.0]
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/134239">...</a>
 */
public class Lv2_134239 {
    public static void main(String[] args) {
        int[][] ranges = {{0,0},{0,-1},{2,-3},{3,-3}};
        System.out.println(Arrays.toString(new Lv2_134239().solution(5, ranges)));
    }
    public double[] solution(int k, int[][] ranges) {
        List<Integer> points = getHailSequence(k);
        List<Double> answer = new ArrayList<>();

        for (int i = 0; i < ranges.length; i++) {
            int x = ranges[i][0];
            int y = points.size() - 1 + ranges[i][1];

            if(x > y) {
                answer.add(-1.0);
                continue;
            }

            int sum = 0;
            for (int j = x; j < y; j++) {
                sum += points.get(j) + points.get(j + 1);
            }
            double area = (double) sum / 2;

            answer.add(area);
        }

        return answer.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }

    private List<Integer> getHailSequence(int k) {
        List<Integer> ints = new ArrayList<>();
        ints.add(k);
        while (k > 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
            ints.add(k);
        }
        return ints;
    }
}
