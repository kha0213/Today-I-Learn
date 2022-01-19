package infLearn;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Question 12 : [Longest Substring With At Most Two Distinct]
 *
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-04-19
 * Time: 오후 8:26
 */
public class Ex12_LongestSubstringWithAtMostTwoDistinct {
    public static void main(String[] args) {
        final int ccaabbb = new Ex12_LongestSubstringWithAtMostTwoDistinct().lengthOfLongestSubstringTwoDistinct("ccaabbb");
        System.out.println("ccaabbb = " + ccaabbb);

    }


    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int start = 0, end = 0, counter = 0;
        int result = 0;

        Map<Character, Integer> map = new HashMap<>();

        while(end < s.length()) {
            char endChar = s.charAt(end);
            map.put(endChar, map.getOrDefault(endChar, 0) + 1);  // char, int
            end++;

            if(map.get(endChar) == 1) {
                counter++;
            }

            while(counter > 2) {
                char startChar = s.charAt(start);
                map.put(startChar,map.get(startChar) - 1);
                if(map.get(startChar) == 0) counter--;
                start++;
            }

            result = Math.max(result, end - start);
        }

        return result;
    }

}
