package goormTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 2021-03-20
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class Test04 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rowColumn = br.readLine().split(" ");
        int row = Integer.parseInt(rowColumn[0]);
        int column = Integer.parseInt(rowColumn[1]);

        int[][] map = new int[row][column];

        for(int i=0;i<row;i++) {
            String[] temp = br.readLine().split(" ");
            for(int j=0;j<column;j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }
        int move = row - 1 + column - 1;
        int result = map[0][0];


    }
}
