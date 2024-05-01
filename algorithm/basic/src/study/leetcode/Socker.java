package study.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Socker {
    public static void main(String[] args) {
        int[][] arr = {{1, 10, 10, 1},
                {2, 1, 2, 3},
                {-1, -1, -1, -1},
                {3, 3, 3, 1},
                {-2, -1, -1, -1}
        };
        System.out.println(goSocker(arr));
    }

    public static List<Integer> goSocker(int[][] arr) {
        PriorityQueue<Human> _1q = new PriorityQueue<>();
        PriorityQueue<Human> _2q = new PriorityQueue<>();
        PriorityQueue<Human> _3q = new PriorityQueue<>();

        Set<Integer> result = new HashSet<>();

        for (int[] ints : arr) {
            if (ints[0] >= 0) {
                _1q.offer(new Human(ints[0], ints[1]));
                _2q.offer(new Human(ints[0], ints[2]));
                _3q.offer(new Human(ints[0], ints[3]));
                continue;
            }

            if (ints[0] == -1) {
                put(result, _1q);
            } else if (ints[0] == -2) {
                put(result, _2q);
            } else {
                put(result, _3q);
            }
        }
        return result.stream().toList();
    }

    public static void put(Set<Integer> result, PriorityQueue<Human> queue) {
        while (true) {
            Human poll = queue.poll();
            if (!result.contains(poll.id)) {
                result.add(poll.id);
                break;
            }
        }
    }

    public static class Human implements Comparable {
        private int id;
        private int score;

        public Human(int id, int score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(Object o) {
            return ((Human) o).score - this.score;
        }
    }

}
