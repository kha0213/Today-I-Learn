package study.bfs;

import java.util.*;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-09.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 * 백준 1012 유기농배추
 *  https://www.acmicpc.net/problem/1012
 */
public class Ex1012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine()); // 테스트케이스 개수
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            result.add(test(sc));
        }
        result.stream().forEachOrdered(System.out::println);
        sc.close();
    }

    private static int test(Scanner sc) {
        int[] data = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = data[0]; //배추밭의 가로길이
        int N = data[1]; //배추밭의 세로길이
        int K = data[2]; //배추가 심어져 있는 위치의 개수
        int[][] map = new int[M][N];

        for (int i = 0; i < K; i ++) {
            int[] location = Arrays.stream(sc.nextLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            map[location[0]][location[1]] = 1;
        }

        return new Ex1012().solution(map);
    }

    private int solution(int[][] map) {
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    count++;
                    bfs(map, i, j);
                }
            }
        }
        return count;
    }

    private void bfs(int[][] map, int i, int j) {
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        map[i][j] = 0;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int[] dir : direction) {
                int x = poll[0] + dir[0];
                int y = poll[1] + dir[1];
                if (x >= 0 && y >= 0 && x < map.length && y < map[0].length && map[x][y] == 1) {
                    map[x][y] = 0;
                    queue.offer(new int[]{x, y});
                }
            }
        }
    }
}
