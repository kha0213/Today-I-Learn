package study.bfs;

import java.util.*;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-09.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 * 백준 2667 단지번호붙이기
 *  https://www.acmicpc.net/problem/2667
 */
public class Ex2667 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int N = Integer.parseInt(input); // 지도의 크기
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i ++) {
            map[i] = Arrays.stream(sc.nextLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        new Ex2667().solution(map);
        sc.close();
    }

    static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
    static int complexCount=0;
    static List<Integer> apartmentCountInComplex = new ArrayList<>();
    private void solution(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    complexCount++;
                    bfs(map, i, j);
                }
            }
        }

        System.out.println(complexCount);
        apartmentCountInComplex.stream().sorted(Integer::compareTo).forEachOrdered(System.out::println);
    }

    private void bfs(int[][] map, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        map[i][j] = 0;
        queue.offer(new int[]{i,j});
        int apartmentCount = 1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int[] dir : direction) {
                int x = poll[0] + dir[0];
                int y = poll[1] + dir[1];
                if (x >= 0 && y >= 0 && x < map.length && y < map[0].length && map[x][y] == 1) {
                    map[x][y] = 0;
                    queue.offer(new int[]{x,y});
                    apartmentCount++;
                }
            }
        }
        apartmentCountInComplex.add(apartmentCount);
    }
}
