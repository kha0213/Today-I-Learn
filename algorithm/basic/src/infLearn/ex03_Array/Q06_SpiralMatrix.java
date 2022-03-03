package infLearn.ex03_Array;

import java.util.ArrayList;
import java.util.List;

public class Q06_SpiralMatrix {
    public static void main(String[] args){
        int[][] input = new int[][]{
                { 1, 2, 3, 4},
                { 5, 6, 7, 8},
                { 9 ,10, 11, 12}
        };
        // result 1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7
        System.out.println(solution(input));
    }



    public static List<Integer> solution(int[][] input) {
        boolean[][] isVisited = new boolean[input.length][input[0].length];
        List<Integer> result = new ArrayList<>();
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int i=0, j=0;
        int index = 0;
        while (!isVisited[i][j]) {
            isVisited[i][j] = true;
            result.add(input[i][j]);

            if (i + direction[index][0] >= input.length || i + direction[index][0] < 0 || j + direction[index][1] < 0 || j + direction[index][1] >= input[0].length
            || isVisited[i + direction[index][0]][j + direction[index][1]]) {
                index = (index+1) % 4;
            }

            i += direction[index][0];
            j += direction[index][1];
        }

        return result;
    }
}
