package study.dynamicProgramming;

import java.util.Arrays;

public class Lv3_IntegerTriangle {
    public static void main(String[] args){
        int[][] triangle = { {7},
                           {3, 8},
                          {8, 1, 0},
                         {2, 7, 4, 4},
                        {4, 5, 2, 6, 5}};
        System.out.println("expected 30 : " + solution(triangle));
    }

    public static int solution(int[][] triangle) {
        int answer = 0;

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    triangle[i][j] += triangle[i - 1][0];
                } else if (j == triangle[i].length - 1) {
                    triangle[i][j] += triangle[i - 1][j - 1];
                } else {
                    triangle[i][j] = Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]) + triangle[i][j];
                }
            }
        }
        return Arrays.stream(triangle[triangle.length - 1]).max().getAsInt();
    }
}
