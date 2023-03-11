package codingTest.t230225;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 쿠팡
 */
public class Ex01 {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public int[] solution(String[] approved, String[] spams, String[] calls, int k) {
        Set<String> spamSet = Arrays.stream(spams).collect(Collectors.toSet());
        Set<String> approvedSet = Arrays.stream(approved).collect(Collectors.toSet());
        Map<String, Integer> checkMap = new HashMap<>();

        List<Integer> result = new ArrayList<>();

        for (String call : calls) {
            if(spamSet.contains(call)) {
                result.add(1);
            } else if (approvedSet.contains(call)) {
                result.add(0);
            } else {
                Integer callCnt = checkMap.getOrDefault(call, 0);
                if (callCnt < k) {
                    result.add(1);
                    checkMap.put(call, callCnt+1);
                } else {
                    result.add(0);
                }
            }
        }
        return result.stream().mapToInt(i->i).toArray();
    }
}
