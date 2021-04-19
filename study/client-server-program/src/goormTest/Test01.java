package goormTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 2021-03-20
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        List<Integer> startTime = new ArrayList<>();
        List<Integer> endTime = new ArrayList<>();
        for( int i = 0; i < num; i ++ ) {
            String temp = br.readLine();
            startTime.add(Integer.parseInt(temp.substring(0,2) + temp.substring(3,5)));
            endTime.add(Integer.parseInt(temp.substring(8,10) + temp.substring(11,13)));
        }
        int end = endTime.stream().min(Integer::compareTo).get();
        int start = startTime.stream().max(Integer::compareTo).get();
        if(start > end) {
            System.out.println("-1");
        } else {

            System.out.println(String.format("%02d", start/100) + ":" + String.format("%02d", start%100)
            + " ~ " + String.format("%02d", end/100) + ":" + String.format("%02d", end%100));
        }
    }
}
