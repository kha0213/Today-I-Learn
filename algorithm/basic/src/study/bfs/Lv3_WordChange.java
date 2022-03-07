package study.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/43163
 */
public class Lv3_WordChange {
    public static void main(String[] args){
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println("expected 4 : " + solution(begin, target, words));
        String[] words2 = {"hot", "dot", "dog", "lot", "log"};
        System.out.println("expected 0 : " + solution(begin, target, words2));

    }

    public static int solution(String begin, String target, String[] words) {
        int count = 0;

        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < words.length; i++) {
            if (canWordChange(begin, words[i])) {
                queue.offer(words[i]);
            }
        }

        while (!queue.isEmpty()) {
            if(count > words.length) return 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (target.equals(word)) {
                    return count + 1; // begin + target
                }
                for (int j = 0; j < words.length; j++) {
                    if (canWordChange(word, words[j])) {
                        queue.offer(words[j]);
                    }
                }
            }
            count++;
        }

        return count;
    }

    public static boolean canWordChange(String before, String after) {
        char[] beforeChar = before.toCharArray();
        char[] afterChar = after.toCharArray();
        int count = 0;

        for (int i = 0; i < beforeChar.length; i++) {
            if (count > 1) {
                return false;
            }
            if (beforeChar[i] != afterChar[i]) {
                count++;
            }
        }
        return count == 1;
    }
}
