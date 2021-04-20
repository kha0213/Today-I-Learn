package infLearn;

import java.util.Arrays;

/**
 *
 * Question 1 : [Number Of Island]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-20.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Graph_NumberOfIsland {

    public static void main(String[] args) {
        char[][] grid = {   {'1','1','1','0','1'},
                            {'1','0','0','0','0'},
                            {'1','1','0','0','1'},
                            {'0','0','0','1','0'}
        };

        System.out.println("numsIslands(grid) = " + numsIslands(grid));
    }

    public static int numsIslands(char[][] grid) {
        int result = 0;
        
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length;j++) {
                if(grid[i][j] == '1') {
                    result++;
                    dps(grid,i,j);
                }
            }
        }
        System.out.println("================final grid =================== \r\n" + Arrays.deepToString(grid));
        return result;
    }

    private static void dps(char[][] grid, int i, int j) {
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j] != '1') return;
        grid[i][j] = 'X';
        dps(grid,i-1,j);
        dps(grid,i+1,j);
        dps(grid,i,j-1);
        dps(grid,i,j+1);
    }

}
