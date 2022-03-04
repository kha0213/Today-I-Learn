package infLearn.ex05_graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q04_WordLadder {
    public static void main(String[] args){
        String beginWord = "hit", endWord="cog";
        List<String> wordList = List.of("hot","dot","dog","lot","log","cog");

        System.out.println(solution(beginWord, endWord, wordList));
    }

    public static int solution(String beginWord, String endWord, List<String> wordList) {
        int result = 0;

        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < wordList.size(); i++) {
            if (isNextWordLadder(beginWord, wordList.get(i))) {
                if(wordList.get(i).equals(endWord)) return 2;
                queue.offer(wordList.get(i));
            }
        }

        while (!queue.isEmpty()) {

            result++;

            if(result > wordList.size()) return 0;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (endWord.equals(poll)) {
                    return result + 1; // 맨 처음 단어 더하기
                }

                for (int j = 0; j < wordList.size(); j++) {
                    if (isNextWordLadder(poll, wordList.get(j))) {
                        queue.offer(wordList.get(j));
                    }
                }
            }
        }

        return result;
    }

    public static boolean isNextWordLadder(String before, String after) {
        char[] beforeChar = before.toCharArray();
        char[] afterChar = after.toCharArray();

        int count = 0;
        for (int i = 0; i < beforeChar.length; i++) {
            if (beforeChar[i] != afterChar[i]) {
                count++;
            }

            if (count > 1) {
                return false;
            }
        }
        return count == 1;
    }
}
