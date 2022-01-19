package study.bfs;

import java.util.*;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-09.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 * 백준 2606 바이러스
 *  https://www.acmicpc.net/problem/2606
 */
public class Ex2606 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine()); // 컴퓨터의 수
        int M = Integer.parseInt(sc.nextLine()); // 컴퓨터 쌍의 수
        int[][] connectInfo = new int[M][2];
        for (int i = 0; i < M; i ++) {
            connectInfo[i] = Arrays.stream(sc.nextLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        new Ex2606().solution(connectInfo);
        sc.close();
    }

    Set<Integer> virusNumber = new HashSet<>();
    private void solution(int[][] connectInfo) {
        Comparator<int[]> comp = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1 != o2) {
                    return Integer.compare(o1[0], o2[0]);
                } else {
                    return Integer.compare(o1[1], o2[1]);
                }

            }
        };
        Arrays.sort(connectInfo,comp);
        bfs(connectInfo);
        System.out.println(virusNumber.size() - 1);
    }

    private void bfs(int[][] connectInfo) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        virusNumber.add(1);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            int[] links = Arrays.stream(connectInfo)
                    .filter(i -> i[0] == poll || i[1] == poll)
                    .mapToInt(i -> i[0] == poll ? i[1] : i[0])
                    .toArray();
            for (int num : links) {
                if (!virusNumber.contains(num)) {
                    queue.add(num);
                    virusNumber.add(num);
                }
            }
        }
    }
}
