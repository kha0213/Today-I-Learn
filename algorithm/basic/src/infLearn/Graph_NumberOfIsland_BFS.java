package infLearn;

import java.util.Arrays;
import java.util.LinkedList;
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
public class Graph_NumberOfIsland_BFS {

    public static void main(String[] args) {
        char[][] grid = {   {'1','1','1','0','1'},
                            {'1','0','0','0','0'},
                            {'1','1','0','0','1'},
                            {'0','0','0','1','0'}
        };

        System.out.println("numsIslandsBFS(grid) = " + numsIslandsBFS(grid));
    }

    public static int numsIslandsBFS(char[][] grid) {
        int result = 0;
        
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length;j++) {
                if(grid[i][j] == '1') {
                    result++;
                    bfs(grid,i,j);
                }
            }
        }
        System.out.println("================final grid =================== \r\n" + Arrays.deepToString(grid));
        return result;
    }

    private static void bfs(char[][] grid, int i, int j) {
        int[][] direction = {{-1,0},{1,0},{0,1},{0,-1}};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] poll = queue.poll();

                for(int k=0;k<size;k++) {
                    for (int[] dire : direction) {
                        int x1 = poll[0] + dire[0];
                        int y1 = poll[1] + dire[1];
                    if(x1 >= 0 && y1 >= 0 && x1 < grid.length && y1 < grid[0].length && grid[x1][y1] == '1') {
                        grid[x1][y1] = '0';
                        queue.offer(new int[]{x1,y1});
                    }
                }
            }
        }
    }

}
