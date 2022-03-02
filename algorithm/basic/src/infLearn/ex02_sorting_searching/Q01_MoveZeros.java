package infLearn.ex02_sorting_searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Q01_MoveZeros {
    public static void main(String[] args){
      Q01_MoveZeros ex = new Q01_MoveZeros();
      int[] inputs = {0,3,2,0,8,5};
      System.out.println(Arrays.toString(ex.solution(inputs)));
    }
    
    public int[] solution(int[] input) {
        List<Integer> result = new ArrayList<>();
        for (int i : input) {
            if(i != 0) {
                result.add(i);
            }
        }

        for (int i = result.size(); i < input.length; i++) {
            result.add(0);
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
