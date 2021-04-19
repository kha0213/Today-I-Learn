package infLearn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *  Question 7 : [JewelsAndStones]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Ex07_JewelsAndStones {

    public static void main(String[] args) {
        String jew = "aA";
        String stone = "aAAbbbb";
        System.out.println("solve : " + solve(jew,stone));
    }

    public static int solve(String jew, String stone) {
        char[] chars = jew.toCharArray();
        Set<Character> search = new HashSet<>();
        for(char c : chars) {
            search.add(c);
        }

        int result = 0;

        for(char c : stone.toCharArray()) {
            if(search.contains(c)) result++;
        }

        return result;
    }

}
