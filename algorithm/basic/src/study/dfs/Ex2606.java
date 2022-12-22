package study.dfs;

import java.util.*;

/**
 * <a href="https://www.acmicpc.net/problem/2606">...</a>
 */
public class Ex2606 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int computerCount = Integer.parseInt(sc.nextLine());
        int N = Integer.parseInt(sc.nextLine()); // 컴퓨터 쌍의 수
        Map<Integer, Set<Integer>> set = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String[] s = sc.nextLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);

            Set<Integer> connectByX = set.getOrDefault(x, new HashSet<>());
            connectByX.add(y);
            set.put(x, connectByX);

            Set<Integer> connectByY = set.getOrDefault(y, new HashSet<>());
            connectByY.add(x);
            set.put(y, connectByY);
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        Set<Integer> visit = new HashSet<>();
        visit.add(1);

        while (!stack.empty()) {
            int pop = stack.pop();
            Set<Integer> connectSet = set.get(pop);
            for (int connect : connectSet) {
                if (!visit.contains(connect)) {
                    visit.add(connect);
                    stack.push(connect);
                }
            }
        }

        System.out.println(visit.size() - 1);
    }
}
