package infLearn.ex01_string;

import java.util.HashSet;
import java.util.Set;

public class Q02_JewelsAndStones {
    public static void main(String[] args){
      Q02_JewelsAndStones ex = new Q02_JewelsAndStones();
        String jewels = "aA";
        String stones = "aAAbbbb";

      System.out.println(ex.solution(jewels, stones));
    }

    public int solution(String jewels, String stones) {
        int result = 0;
        Set<Character> jewel = new HashSet<>();
        for (char c : jewels.toCharArray()) {
            jewel.add(c);
        }

        for (char c : stones.toCharArray()) {
            if(jewel.contains(c))
                result++;
        }
        return result;
    }
}
