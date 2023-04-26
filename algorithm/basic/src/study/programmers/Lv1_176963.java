package study.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public class Lv1_176963 {
    public static void main(String[] args) {
        String[] name = {"may", "kein", "kain", "radi"};
        int[] yearning = {5, 10, 1, 3};
        String[][] photo = {{"may", "kein", "kain", "radi"}, {"may", "kein", "brin", "deny"}, {"kon", "kain", "may", "coni"}};
        // 19,15,6
        System.out.println(Arrays.toString(new Lv1_176963().solution(name, yearning, photo)));
    }

    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            map.put(name[i], yearning[i]);
        }

        return stream(photo)
                .mapToInt(arr -> stream(arr).mapToInt(nm -> map.getOrDefault(nm, 0)).sum())
                .toArray();
    }
}
