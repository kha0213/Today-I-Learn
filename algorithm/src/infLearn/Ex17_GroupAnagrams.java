package infLearn;

import java.util.*;

/**
 *
 * Question 17 : [Group Anagrams]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-20.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Ex17_GroupAnagrams {
    public static void main(String[] args) {
        String[] list = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(list));
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> result = new HashMap<>();
        for(String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            List<String> orDefault = result.getOrDefault(String.valueOf(chars), new ArrayList<>());
            orDefault.add(s);
            result.put(String.valueOf(chars), orDefault);
        }
        List<List<String>> rtn = new ArrayList<>();
        rtn.addAll(result.values());
        return rtn;
    }

}
