package infLearn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * Question 2 : [Number Of Island BFS]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-20.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Graph_NumberOfIsland_BFS2 {



    public static void main(String[] args) {
        char[][] grid = {   {'1','1','0','1','1'},
                            {'1','0','0','1','0'},
                            {'1','1','0','0','1'},
                            {'0','0','0','1','1'}
        };

        System.out.println("numsIslandsBFS(grid) = " + numsIslandsBFS(grid));
    }

    public static int numsIslandsBFS(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    static int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static void bfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int[] dir : direction) {
                bfs(grid,poll[0]+dir[0],poll[1]+dir[1]);
            }
        }


    }

}
