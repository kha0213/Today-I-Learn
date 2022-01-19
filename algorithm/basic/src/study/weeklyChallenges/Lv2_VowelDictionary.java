package study.weeklyChallenges;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-16.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv2_VowelDictionary {
    public static void main(String[] args){
        String word = "AAAAE";
        System.out.println("AAAAE[6] : " + new Lv2_VowelDictionary().solution(word));
        String word2 = "AAAE";
        System.out.println("AAAE[10] : " + new Lv2_VowelDictionary().solution(word2));
        String word3 = "I";
        System.out.println("I[1563] : " + new Lv2_VowelDictionary().solution(word3));
        String word4 = "EIO";
        System.out.println("EIO[1189] : " + new Lv2_VowelDictionary().solution(word4));
    }

    public int solution(String word) {
        int[] convertWord = wordToIntArr(word);
        int result = 0;
        for (int i = 0; i < convertWord.length; i++) {
            int idx = 4 - i;
            int temp = 1;
            for (int j = idx; j > 0; j--) {
                temp += (int) Math.pow(5, j);
            }
            result += temp * convertWord[i] + 1;
        }
        return result;
    }

    private int[] wordToIntArr(String word) {
        int[] result = new int[word.length()];
        for (int i = 0; i < word.length(); i++) {
            switch (word.charAt(i)) {
                case 'A' : result[i] = 0; break;
                case 'E' : result[i] = 1; break;
                case 'I' : result[i] = 2; break;
                case 'O' : result[i] = 3; break;
                case 'U' : result[i] = 4;
            }
        }
        return result;
    }


}
