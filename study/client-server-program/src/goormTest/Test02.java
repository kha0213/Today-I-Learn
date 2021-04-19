package goormTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 2021-03-20
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class Test02 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        String input = br.readLine();
        long result = 1L;
        long pre = 1L;
        long dPre = 0L;
        if(input.charAt(1)=='1' && input.charAt(2)=='1') {
            result = 2;
            pre = 2;
            dPre = 1;
        }

        for(int i=3;i<length;i++) {
            if(input.charAt(i)=='0') {
                result = pre;
                dPre = 0;
            } else {
                result = pre + dPre;
                dPre = input.charAt(i-1)=='0'?0:pre;
                pre = result;
            }
        }

        System.out.println(result);
    }
}
