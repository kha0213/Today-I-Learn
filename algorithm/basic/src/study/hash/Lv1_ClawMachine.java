package study.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * 2021-01-20
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 2019 카카오 개발자 겨울 인턴쉽 > 크레인 인형뽑기 게임
 * https://programmers.co.kr/learn/courses/30/lessons/64061
 */
public class Lv1_ClawMachine {
    public static void main(String[] args) {
        Lv1_ClawMachine ex = new Lv1_ClawMachine();

        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};
        System.out.println(ex.solution(board,moves));
    }
    //{1,2,3},{2,3,4},{4,5,6}
    public int solution(int[][] board, int[] moves) {
        Stack<Integer> resultStack = new Stack<>();
        resultStack.push(Integer.MAX_VALUE); // 임시로
        Stack[] boardStack = new Stack[board.length];
        for(int i=0; i<board.length;i++){
            boardStack[i] = new Stack();
        }
        for(int i=board.length-1; i>=0;i--){
            for(int j=0;j<board.length;j++){
                if(board[i][j]!=0){
                    boardStack[j].push(board[i][j]);
                }
            }
        }
        int boomDoll = 0;
        for (int i=0; i<moves.length;i++){
            int moveRoll = moves[i]-1;
            if(!boardStack[moveRoll].empty()){
                int claw = (int) boardStack[moveRoll].pop();
                if(resultStack.peek() == claw){
                    resultStack.pop();
                    boomDoll +=2;
                }else {
                    resultStack.push(claw);
                }
            }
        }
        return boomDoll;
    }
}
