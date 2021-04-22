package infLearn;

import java.util.Arrays;

/**
 *
 * Question 6 : [Max Area Of Islands]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-20.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Graph_MaxAreaOfIsland {

    public static void main(String[] args) {
        char[][] grid = {   {'1','1','1','0','1'},
                            {'1','0','0','0','0'},
                            {'1','1','0','0','1'},
                            {'0','0','0','1','1'}
        };

        System.out.println("maxAreaIsland(grid) = " + maxAreaIsland(grid));
    }

    static int temp = 0;
    public static int maxAreaIsland(char[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dps(grid,i,j);
                }
                max = Math.max(max, temp);
                temp = 0;
            }
        }

        System.out.println("================final grid =================== \r\n" + Arrays.deepToString(grid));
        return max;
    }

    private static void dps(char[][] grid, int i, int j) {

        if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] == '1') {
            grid[i][j] = 'X';
            temp++;
            dps(grid,i+1,j);
            dps(grid,i-1,j);
            dps(grid,i,j+1);
            dps(grid,i,j-1);
        }

    }

}
