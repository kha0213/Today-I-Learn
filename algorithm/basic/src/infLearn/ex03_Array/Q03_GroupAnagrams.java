package infLearn.ex03_Array;

import java.lang.reflect.Array;
import java.util.*;

public class Q03_GroupAnagrams {
    public static void main(String[] args){
      Q03_GroupAnagrams ex = new Q03_GroupAnagrams();
        String[] input = {"ab","ba","ab","eat", "tea", "tan", "ate", "nat", "bat"};
      System.out.println(Arrays.deepToString(ex.solution(input)));
    }
    
    public String[][] solution(String[] input) {
        Arrays.sort(input);
        //ate, bat, eat, nat, tan, tea
        Map<String, List<String>> map = new HashMap<>();
        for (String word : input) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortStr = String.valueOf(chars);
            List<String> list = map.getOrDefault(sortStr, new ArrayList<>());
            list.add(word);
            map.put(sortStr, list);
        }
        List<String[]> result = new ArrayList<>();

        for (List<String> value : map.values()) {
            result.add(value.toArray(new String[0]));
        }
        return result.toArray(new String[0][]);
    }
}
