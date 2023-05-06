package study.string;

import java.util.Arrays;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/161990">...</a>
 */
public class Ex161990 {
    public static void main(String[] args) {
        String[] wallpaper = {"..........",
                              ".....#....",
                              "......##..",
                              "...##.....",
                              "....#....."};
        int[] solution = new Ex161990().solution(wallpaper);
        // 1,3,5,8
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public int[] solution(String[] wallpaper) {
        int minI = Integer.MAX_VALUE, minJ = Integer.MAX_VALUE, maxI = 0, maxJ = 0;

        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    minI = Math.min(minI, i);
                    minJ = Math.min(minJ, j);
                    maxI = Math.max(maxI, i);
                    maxJ = Math.max(maxJ, j);
                }
            }
        }

        return new int[]{minI, minJ, maxI + 1, maxJ + 1};
    }
}
