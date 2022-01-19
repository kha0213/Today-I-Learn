package infLearn;

import java.util.*;

/**
 *
 * Question 7 : [Word Ladder]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-20.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Graph_WordLadder {
    public static int ladder(String beginWord, String endWord, List<String> wordList){
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int result = 1;
        all : while (true) {
            int size = queue.size();
            result++;

            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (String word : wordList) {
                    if(goLadder(poll,word)) {
                        if(word.equals(endWord)) {
                            break all;
                        }
                        queue.offer(word);
                    }
                }
            }
        }
        return result;
    }

    private static boolean goLadder(String poll, String word) {
        int count = 0;
        char[] pollChar = poll.toCharArray();
        char[] chars = word.toCharArray();
        for (int i=0; i<pollChar.length; i++) {
            if(pollChar[i] == chars[i]) count++;
        }
        return count == pollChar.length - 1;
    }

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        String beginWord = "hit";
        String endWord = "cog";
        System.out.println("ladder(beginWord,endWord, wordList) = " + ladder(beginWord,endWord, wordList));
    }



}
