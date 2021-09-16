package infLearn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-16.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Graph_Maze1_dfs {
    public static void main(String[] args) {
        int[][] maze ={  {0,0,1,0,0}
                        ,{0,0,0,0,0}
                        ,{0,0,0,1,0}
                        ,{1,1,0,1,1}
                        ,{0,0,0,0,0}};
        int[] start = {0, 4};
        int[] destination = {4, 4};
        System.out.println(new Graph_Maze1_dfs().hasPath(maze, start, destination));
    }

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        dfs(maze, start);
        return maze[destination[0]][destination[1]] == 2;
    }

    private void dfs(int[][] maze, int[] position) {
        int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int x = position[0];
        int y = position[1];
        if(x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] != 0) {
            return;
        }
        maze[x][y] = 2;
        Arrays.stream(maze).forEachOrdered(a ->
                System.out.println(Arrays.toString(a))
        );
        System.out.println("=========================================");
        for (int[] dir : direction) {
            dfs(maze,wallArrive(maze,position,dir));
        }
    }

    private int[] wallArrive(int[][] maze, int[] position, int[] dir) {
        int[] result = {position[0] + dir[0], position[1] + dir[1]};

        while (result[0] >= 0 && result[1] >= 0 && result[0] < maze.length && result[1] < maze[0].length && maze[result[0]][result[1]] != 1) {
            result[0] += dir[0];
            result[1] += dir[1];
        }
        result[0] -= dir[0];
        result[1] -= dir[1];
        return result;
    }
}
