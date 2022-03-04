package infLearn.ex05_graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Q01_NumberOfIsland_DFS_BFS {
    public static void main(String[] args){
        String[][] input = {
                {"1","1","0","1","1"},
                {"1","1","0","1","0"},
                {"1","1","0","0","0"},
                {"0","0","0","1","1"}
        };
        System.out.println("dfs : " + solution_dfs(input));

        String[][] input2 = {
                {"1","1","0","1","1"},
                {"1","1","0","1","0"},
                {"1","1","0","0","0"},
                {"0","0","0","1","1"}
        };
        System.out.println("bfs : " + solution_bfs(input2));
    }
    
    public static int solution_dfs(String[][] grid) {
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].equals("1")) {
                    result++;
                    dfs(grid, i, j);
                }
            }
        }
        System.out.println("=====dfs print");
        print(grid);
        return result;
    }

    private static void print(String[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            sb.append(Arrays.stream(grid[i]).collect(Collectors.joining(",", "[", "]")) + "\r\n");
        }
        System.out.println(sb);
    }

    public static int solution_bfs(String[][] grid) {
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].equals("1")) {
                    result++;
                    bfs(grid, i, j);
                }
            }
        }
        System.out.println("=====bfs print");
        print(grid);
        return result;
    }

    private static void bfs(String[][] grid, int m, int n) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{m, n});
        grid[m][n] = "X";

        int[][] direction = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] dir : direction) {
                int x = point[0] + dir[0];
                int y = point[1] + dir[1];
                if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y].equals("1")) {
                    grid[x][y] = "X";
                    queue.offer(new int[]{x, y});
                }
            }
        }
    }

    private static void dfs(String[][] grid, int m, int n) {
        if (m < 0 || n < 0 || m >= grid.length || n >= grid[0].length || !grid[m][n].equals("1")) {
            return;
        }

        grid[m][n] = "x";

        dfs(grid, m-1, n);
        dfs(grid, m+1, n);
        dfs(grid, m, n-1);
        dfs(grid, m, n+1);

    }
}
