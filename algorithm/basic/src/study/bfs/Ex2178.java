package study.bfs;

import java.util.*;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-09.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 * 백준 2178 미로탐색
 *  https://www.acmicpc.net/problem/2178
 */
public class Ex2178 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] temp = input.split(" ");
        int N = Integer.parseInt(temp[0]); // N개 줄
        int M = Integer.parseInt(temp[1]); // M개의 정수로 미로

        int[][] maze = new int[N][M];
        for (int i = 0; i < N; i ++) {
            maze[i] = Arrays.stream(sc.nextLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        int result = new Ex2178().solution(maze);
        System.out.println(result);
        sc.close();
    }
    static int result = Integer.MAX_VALUE;
    static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
    private int solution(int[][] maze) {
        bfs(maze,new Point(0, 0, 1));
        return result;
    }

    private void bfs(int[][] maze, Point point) {
        Queue<Point> queue = new LinkedList<>();
        maze[0][0] = 0;
        queue.offer(point);

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            for (int[] dir : direction) {
                Point newPoint = new Point(poll, dir);
                if (newPoint.row == maze.length-1 && newPoint.col == maze[0].length-1) {
                    result = Math.min(result,newPoint.depth);
                }
                if (newPoint.col >= 0 && newPoint.row >= 0 && newPoint.row < maze.length && newPoint.col < maze[0].length && maze[newPoint.row][newPoint.col] == 1) {
                    maze[newPoint.row][newPoint.col] = 0;
                    queue.offer(newPoint);
                }
            }
        }
    }

    static class Point {
        int row;
        int col;
        int depth;
        public Point(int row, int col, int depth) {
            this.row = row;
            this.col = col;
            this.depth = depth;
        }

        public Point(Point oldPoint, int[] dir) {
            this.row = oldPoint.row + dir[0];
            this.col = oldPoint.col + dir[1];
            this.depth = oldPoint.depth + 1;
        }
    }
}
