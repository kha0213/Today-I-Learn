package study.bfs;

import java.util.*;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-07.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 * 백준 1260 DFS와 BFS
 * https://www.acmicpc.net/problem/1260
 */
public class Ex1260 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] temp = input.split(" ");
        int N = Integer.parseInt(temp[0]); // 정점의 개수
        int M = Integer.parseInt(temp[1]); // 간선의 개수
        int V = Integer.parseInt(temp[2]); // 시작 정점의 번호
        int[][] line = new int[M][2];
        Set<Integer> isVisited = new HashSet<>();

        for (int i = 0; i <M; i++) {
            String[] inputLine = sc.nextLine().split(" ");
            int num1 =Integer.parseInt(inputLine[0]);
            int num2 =Integer.parseInt(inputLine[1]);
            line[i] = new int[]{num1, num2};
        }

        Arrays.sort(line,(i,j)->Integer.compare(i[0]*10+i[1],j[0]*10+j[1]));

        for (int i = 0; i < M; i++) {
            if(line[i][0] == V){
                V = i;
                break;
            }
        }
        isVisited.add(line[V][0]);
        List<Integer> answer = new ArrayList<>();
        answer.add(line[V][0]);
        dfs(line, isVisited, V, answer);

        System.out.println("solution = " + answer.toString());


    }

    private static void dfs(int[][] line, Set<Integer> isVisited, int idx, List<Integer> answer) {
        if(isVisited.contains(line[idx][1])){
            return;
        }
        isVisited.add(line[idx][1]);
        answer.add(line[idx][1]);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(line[idx][0]);
        queue.offer(line[idx][1]);
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            for (int i = 0; i < line.length; i++) {
                if (line[i][0] == poll) {
                    dfs(line,isVisited,i,answer);
                }
            }
        }
    }

}
