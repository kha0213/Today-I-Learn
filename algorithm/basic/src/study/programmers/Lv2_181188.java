package study.programmers;

import java.util.Arrays;

public class Lv2_181188 {
    public static void main(String[] args) {
        int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
        //int[][] targets = {{1,2},{2,3},{3,4},{5,12},{3,7},{1,4}};
        Lv2_181188 lv2_181188 = new Lv2_181188();
        System.out.println(lv2_181188.solution(targets));
    }

    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> {
            if( a[0] == b[0] )
                return a[1] - b[1];
            else
                return a[0] - b[0];
        });

        int answer = 1;
        int before = targets[0][1];

        for (int i = 1; i < targets.length; i++) {
            if ( targets[i][0] >= before ) {
                answer++;
                before = targets[i][1];
            } else {
                before = Math.min(before, targets[i][1]);
            }
        }

        return answer;
    }
}
