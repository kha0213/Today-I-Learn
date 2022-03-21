package codingTest.t220319;

import java.util.*;

public class Q2_01 {
    public static void main(String[] args){
      Q2_01 ex = new Q2_01();
      int n = 6;
      int k = 17;
      int[][] roads = {{5,4,6},{5,2,5},{0,4,2},{2,3,3},{1,2,7},{0,2,3}};
      System.out.println(Arrays.toString(ex.solution(n, k, roads)));
    }

    public int[] solution(int n, int k, int[][] roads) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] road : roads) {
            List<int[]> list = map.getOrDefault(road[0], new ArrayList<>());
            list.add(new int[]{road[1], road[2]});
            map.put(road[0], list);
            List<int[]> list2 = map.getOrDefault(road[1], new ArrayList<>());
            list2.add(new int[]{road[0], road[2]});
            map.put(road[1], list2);
        }
        Set<Integer> result = new TreeSet<>();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0,k});

        while(!stack.isEmpty()){
            int[] pop = stack.pop();
            List<int[]> locations = map.get(pop[0]);
            for (int[] loc : locations) {
                if(pop[1] == loc[1]) {
                    result.add(loc[0]);
                } else if (loc[1] < pop[1]) {
                    stack.push(new int[]{loc[0], pop[1] - loc[1]});
                }
            }
        }

        if(result.size() == 0) return new int[]{-1};

        return result.stream().mapToInt(i->i).toArray();
    }
}
