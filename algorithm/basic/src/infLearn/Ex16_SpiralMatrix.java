package infLearn;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Question 16 : [Spiral Matrix]
 *
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-04-19
 * Time: 오후 11:21
 */
public class Ex16_SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        //int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        System.out.println(solve(matrix));
    }
    public static List<Integer> solve(int[][] matrix) {
        int rowStart = 0;
        int rowEnd = matrix.length -1;
        int colStart = 0;
        int colEnd = matrix[0].length -1;

        int row = 0;
        int col = 0;

        List<Integer> result = new ArrayList<>();

        int direction = 0; //0 오른쪽, 1 아래, 2 왼쪽, 3 위

        while (result.size() < matrix.length * matrix[0].length) {
            result.add(matrix[row][col]);
            switch (direction) {
                case 0:
                    if (col == colEnd) {
                        direction = 1;
                        rowStart++;
                        row++;
                    } else {
                        col++;
                    }
                    break;
                case 1:
                    if (row == rowEnd) {
                        direction = 2;
                        colEnd--;
                        col--;
                    } else {
                        row++;
                    }
                    break;
                case 2:
                    if (col == colStart) {
                        direction = 3;
                        rowEnd--;
                        row--;
                    } else {
                        col--;
                    }
                    break;
                case 3:
                    if (row == rowStart) {
                        direction = 0;
                        colStart++;
                        col++;
                    } else {
                        row--;
                    }
            }
        }
        return result;
    }


}

