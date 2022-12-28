package study.programmers;

import java.util.*;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/49189">...</a>
 */
public class Lv3_49189 {
    public static void main(String[] args) {
        /**
         * n	vertex	return
         * 6	[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]	3
         */
        int n = 6;
        int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        int result = new Lv3_49189().solution(n, vertex);
        System.out.println("result = " + result);
    }

    public int solution(int n, int[][] edge) {

        Map<Integer, Set<Integer>> map = getRoadMap(edge);
        Set<Integer> visit = new HashSet<>();
        visit.add(1);

        Stack<Set<Integer>> stack = new Stack<>();
        stack.push(Set.of(1));

        int answer = 0;
        while (!stack.empty()) {
            Set<Integer> pop = stack.pop();
            answer = pop.size();
            Set<Integer> newStack = new HashSet<>();
            for (int item : pop) {
                Set<Integer> integers = map.get(item);
                for (int num : integers) {
                    if(!visit.contains(num)) {
                        visit.add(num);
                        newStack.add(num);
                    }
                }
            }
            if(!newStack.isEmpty())
                stack.push(newStack);
        }
        return answer;
    }

    private Map<Integer, Set<Integer>> getRoadMap(int[][] edge) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] connect : edge) {
            Set<Integer> set = map.getOrDefault(connect[0], new HashSet<>());
            set.add(connect[1]);
            map.put(connect[0], set);
            Set<Integer> set2 = map.getOrDefault(connect[1], new HashSet<>());
            set2.add(connect[0]);
            map.put(connect[1], set2);
        }
        return map;
    }
}
