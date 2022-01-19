package infLearn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * Question 8 : [Word Search]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-20.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Graph_WordSearch {
    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };

        String s1 = "ABCCED";
        String s2 = "SEE";
        String s3 = "ABCB";
        String s4 = "BCESC";
        String s5 = "ABCEC";

        System.out.println("s1 = " + solution(board, s1));
        System.out.println("s2 = " + solution(board, s2));
        System.out.println("s3 = " + solution(board, s3));
        System.out.println("s4 = " + solution(board, s4));
        System.out.println("s5 = " + solution(board, s5));
    }

    private static boolean solution(char[][] board, String word) {
        boolean result = false;

        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) {

                    if(search(board, visited, i, j, 0, word)) {
                        return true;
                    }
                }
            }
        }
        return result;
    }

    private static boolean search(char[][] board, boolean[][] visited, int i, int j, int count, String word) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length ) {
            return false;
        }
        if(board[i][j] != word.charAt(count)) {
            return false;
        }

        if (visited[i][j]) return false;


        if (count == word.length() - 1 && board[i][j] == word.charAt(count)) {
            return true;
        }
        visited[i][j] = true;
        return search(board, visited,i - 1, j, count + 1, word) ||
        search(board, visited,i + 1, j, count + 1, word) ||
        search(board, visited, i, j - 1, count + 1, word) ||
        search(board, visited, i, j + 1, count + 1, word);
    }
}
