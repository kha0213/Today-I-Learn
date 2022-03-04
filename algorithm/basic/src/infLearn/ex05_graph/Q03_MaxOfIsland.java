package infLearn.ex05_graph;

import java.util.LinkedList;
import java.util.Queue;

public class Q03_MaxOfIsland {
    public static void main(String[] args){
        int[][] input = {
                {1, 1, 0, 1, 1},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 0, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1}
        };

        int[][] input2 = new int[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            input2[i] = input[i].clone();
        }

        System.out.println("bfs : " + solution_bfs(input2));
        System.out.println("dfs : " + solution_dfs(input));
    }
    
    public static int solution_dfs(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result = Math.max(result, dfs(grid, i, j));
                }
            }
        }
        return result;
    }

    private static int dfs(int[][] grid, int m, int n) {
        if (m < 0 || n < 0 || m >= grid.length || n >= grid[0].length || grid[m][n] != 1) {
            return 0;
        }

        grid[m][n] = 0;
        int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        int count = 1;
        for (int[] dir : direction) {
            count += dfs(grid, m + dir[0], n + dir[1]);
        }
        return count;
    }

    public static int solution_bfs(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result = Math.max(result, bfs(grid, i, j));
                }
            }
        }
        return result;
    }

    private static int bfs(int[][] grid, int m, int n) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{m, n});
        grid[m][n] = 0;
        int count = 1;

        int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] dir : direction) {
                int x = point[0] + dir[0];
                int y = point[1] + dir[1];
                if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1) {
                    queue.offer(new int[]{x, y});
                    grid[x][y] = 0;
                    count++;
                }
            }
        }
        return count;
    }
}
