package study.practice;

import java.util.Arrays;

/**
 * 2021-01-27
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class Lv2_LargestSquare {
    static int wid = 0;
    static int hei = 0;
    public int solution(int[][] board) {
        int side = 1;
        wid = board.length;
        hei = board[0].length;
        for(int i=0; i+side<wid;i++){
            for(int j=0;j+side<hei;j++){
                if(board[i][j] == 1)
                    side = maxSide(board, side, i, j);
            }
        }
        return side*side;
    }

    public int maxSide(int[][] board, int side, int i, int j) {
        int temp = side;
        all : while(true) {
            if(i+temp>=wid || j+temp>=hei) break;

            for(int k=0; k <= temp; k++){
                if(board[i+k][j+temp] == 0 || board[i+temp][j+k] == 0) break all;
            }
            temp++;
        }
        return temp;
    }
}
