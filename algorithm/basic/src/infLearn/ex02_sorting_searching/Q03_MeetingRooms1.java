package infLearn.ex02_sorting_searching;

import java.util.Arrays;
import java.util.Comparator;

public class Q03_MeetingRooms1 {
    public static void main(String[] args){
      Q03_MeetingRooms1 ex = new Q03_MeetingRooms1();
      int[][] inputs = {
                {5,10},
                {16,20},
                {0,2}

      };
      System.out.println(ex.solution(inputs));
    }
    
    public boolean solution(int[][] input) {
        Arrays.sort(input, Comparator.comparingInt(a -> a[0]));
        System.out.println("Arrays.deepToString(input) = " + Arrays.deepToString(input));

        for (int i = 1; i < input.length; i++) {
            if(input[i - 1][1] > input[i][0]) return false;
        }
        return true;
    }
}
