package study.dfs;

import java.util.*;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-09.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 * 백준 11724 연결 요소의 개수
 *  https://www.acmicpc.net/problem/11724
 */
public class Ex11724 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split(" ");
        int N = Integer.parseInt(split[0]); // 정점의 개수
        int M = Integer.parseInt(split[1]); // 간선의 개수
        int[][] graph = new int[M][2];
        for (int i = 0; i < M; i++) {
            String[] link = sc.nextLine().split(" ");
            graph[i] = new int[]{Integer.parseInt(link[0]), Integer.parseInt(link[1])};
        }
        System.out.println(new Ex11724().solution(graph, N, M));
        sc.close();
    }

    private int solution(int[][] graph, int N, int M) {
        Arrays.sort(graph, (o1, o2) -> o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int i = 0; i < graph.length; i++) {
            if (visited.contains(graph[i][0]) || visited.contains(graph[i][1])) {
                
            }
        }
        dfs(graph,visited);
        return count;
    }

    private void dfs(int[][] graph, Set<Integer> visited) {
    }
}
