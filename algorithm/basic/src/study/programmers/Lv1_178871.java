package study.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lv1_178871 {
    public static void main(String[] args) {
        Lv1_178871 lv = new Lv1_178871();
        String[] players = {"mumu", "soe", "poe", "kai", "mine"};
        String[] callings = {"kai", "kai", "mine", "mine"};
        // "mumu", "kai", "mine", "soe", "poe"
        String[] result = lv.solution(players, callings);
        System.out.println(Arrays.toString(result));
    }

    public String[] solution(String[] players, String[] callings) {
        Map<Integer, String> rankMap = new HashMap<>();
        Map<String, Integer> nameMap = new HashMap<>();

        for (int i = 0; i < players.length; i++) {
            rankMap.put(i, players[i]);
            nameMap.put(players[i], i);
        }

        for (String call : callings) {
            Integer rank = nameMap.get(call);
            String before = rankMap.get(rank - 1);

            rankMap.put(rank - 1, call);
            rankMap.put(rank, before);
            nameMap.put(call, rank - 1);
            nameMap.put(before, rank);
        }
        return rankMap.values().toArray(new String[0]);
    }
}
