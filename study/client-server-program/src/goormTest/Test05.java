package goormTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 2021-03-20
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class Test05 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int line = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[line][line];


        for(int i=0; i<line; i++) {
            String temp = br.readLine();
            for(int j=0; j<line; j++) {
                map[i][j] = temp.charAt(j) == '1';
            }
        }

        int oneCount = 0;
        List<Integer> count = new ArrayList<>();


    }
}
