package study.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/43162
 * Lv3 네트워크
 */
public class Lv3_Network {
    public static void main(String[] args){
        int n = 3;
        int[][] computers = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        System.out.println("expected 2 : " + solution(n, computers));

        int n2 = 3;
        int[][] computers2 = {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        System.out.println("expected 1 : " + solution(n2, computers2));
    }

    public static int solution(int n, int[][] computers) {
        int count = 0;
        boolean[] isVisited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (isVisited[i] == false) {
                count++;
                isVisited[i] = true;
                bfs(computers, isVisited, i);
            }
        }
        return count;
    }
    private static void bfs(int[][] computers, boolean[] isVisited, int index) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < computers.length; i++) {
            if (computers[index][i] == 1 && isVisited[i] == false) {
                isVisited[i] = true;
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                for (int j = 0; j < computers.length; j++) {
                    if (computers[poll][j] == 1 && isVisited[j] == false) {
                        isVisited[j] = true;
                        queue.offer(j);
                    }
                }
            }
        }
    }
}
