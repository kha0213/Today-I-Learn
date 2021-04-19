package infLearn;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Question 15 : [Find All Anagrams]
 *
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-04-19
 * Time: 오후 10:44
 */
public class Ex15_FindAllAnagrams {
    public static void main(String[] args) {
        String txt = "BACDGABCDA";
        String pat = "ABCD";
        final List<Integer> anagrams = findAnagrams(txt, pat);
        System.out.println("anagrams = " + anagrams);
    }

    public static List<Integer> findAnagrams(String txt, String pat) {
        Map<Character, Integer> patMap = new HashMap<>();
        for(int i=0; i< pat.length(); i++) {
            patMap.put(pat.charAt(i), i);
        }

        char[] txts = txt.toCharArray();
        List<Integer> result = new ArrayList<>();

        first : for (int i=0; i <= txt.length() - pat.length(); i++) {
            boolean[] checkPoint = new boolean[pat.length()];
            for(int j=i; j<i+pat.length(); j++) {
                if(patMap.get(txts[j]) != null) {
                    checkPoint[patMap.get(txts[j])] = true;
                } else {
                    i += pat.length()-1;
                    continue first;
                }
            }
            if(isAllTrue(checkPoint)) result.add(i);
        }
        return result;
    }

    private static boolean isAllTrue(boolean[] checkPoint) {
        for (boolean b : checkPoint) {
            if(!b) return false;
        }
        return true;
    }

}
