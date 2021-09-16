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
public class Graph_Maze1_bfs {
    public static void main(String[] args) {
        int[][] maze ={  {0,0,1,0,0}
                        ,{0,0,0,0,0}
                        ,{0,0,0,1,0}
                        ,{1,1,0,1,1}
                        ,{0,0,0,0,0}};
        int[] start = {0, 4};
        int[] destination = {4, 4};
        System.out.println(new Graph_Maze1_bfs().hasPath(maze, start, destination));
    }

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        boolean[][] isVisited = new boolean[maze.length][maze[0].length];
        isVisited[start[0]][start[1]] = true;
        queue.offer(start);
        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            for (int[] dir : direction) {
                int[] move = wallArrive(maze, position, dir);
                if (!Arrays.equals(move,position) && !isVisited[move[0]][move[1]]) {
                    if(Arrays.equals(move,destination)) {
                        return true;
                    }
                    isVisited[move[0]][move[1]] = true;
                    queue.offer(move);
                }
            }
        }
        return false;
    }

    private int[] wallArrive(int[][] maze, int[] position, int[] dir) {
        int[] result = {position[0] + dir[0], position[1] + dir[1]};

        while (result[0] >= 0 && result[1] >= 0 && result[0] < maze.length && result[1] < maze[0].length && maze[result[0]][result[1]] == 0) {
            result[0] += dir[0];
            result[1] += dir[1];
        }
        result[0] -= dir[0];
        result[1] -= dir[1];
        return result;
    }
}
