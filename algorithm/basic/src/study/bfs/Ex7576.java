package study.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-09.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 * 백준 7576 토마토
 *  https://www.acmicpc.net/problem/7576
 */
public class Ex7576 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int M = Integer.parseInt(data[0]);
        int N = Integer.parseInt(data[1]);
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        System.out.println(new Ex7576().solution(map));
        br.close();
    }

    private int solution(int[][] map) {
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    queue.offer(new int[]{i,j});
                }
            }
        }
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                map[poll[0]][poll[1]] = 1;
                for (int[] dir : direction) {
                    int x = poll[0] + dir[0];
                    int y = poll[1] + dir[1];
                    if (x >= 0 && y >= 0 && x < map.length && y < map[0].length && map[x][y] == 0) {
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            count++;
        }
        count--;
        return Arrays.stream(map)
                .flatMapToInt(Arrays::stream)
                .noneMatch(grow -> grow == 0)? count : -1;
    }
}
