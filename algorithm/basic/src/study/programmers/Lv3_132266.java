package study.programmers;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/132266">...</a>
 */
public class Lv3_132266 {

    public static void main(String[] args) {
        /**
         * n	roads	                                    sources	    destination	result
         * 3	[[1, 2], [2, 3]]	                        [2, 3]	    1	        [1, 2]
         * 5	[[1, 2], [1, 4], [2, 4], [2, 5], [4, 5]]	[1, 3, 5]	5	        [2, -1, 0]
         */
        int n = 5;
        int[][] roads = {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}};
        int[] sources = {1, 3, 5};
        int destination = 5;

        System.out.println("new Lv3_132266().solution(n,roads,sources,destination) = "
                + Arrays.toString(new Lv3_132266().solution(n, roads, sources, destination)));

    }
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        Map<Integer, List<Integer>> roadMap = getRoadMap(roads);

        Map<Integer, Integer> sourceMap = Arrays.stream(sources)
                .boxed()
                .collect(Collectors.toMap(i -> i, j -> -1));
        if (sourceMap.get(destination) != null) {
            sourceMap.put(destination, 0);
        }

        Set<Integer> visited = new HashSet<>();
        visited.add(destination);

        int count = 0;
        Stack<Set<Integer>> stack = new Stack<>();
        stack.push(Set.of(destination));

        while(!stack.isEmpty()) {
            Set<Integer> distinctSet = stack.pop();

            Set<Integer> nextSet = new HashSet<>();
            count++;
            for (int item : distinctSet) {
                List<Integer> arrivalList = roadMap.getOrDefault(item, new ArrayList<>());
                for (int arrival : arrivalList) {
                    if (!visited.contains(arrival)) {
                        visited.add(arrival);
                        sourceMap.put(arrival, count);
                        nextSet.add(arrival);
                    }
                }
            }
            if(!nextSet.isEmpty())
                stack.push(nextSet);
        }

        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = sourceMap.get(sources[i]);
        }
        return answer;
    }

    private Map<Integer, List<Integer>> getRoadMap(int[][] roads) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] road : roads) {
            List<Integer> list = map.getOrDefault(road[0], new ArrayList<>());
            list.add(road[1]);
            map.put(road[0], list);

            List<Integer> list2 = map.getOrDefault(road[1], new ArrayList<>());
            list2.add(road[0]);
            map.put(road[1], list2);
        }
        return map;
    }
}
