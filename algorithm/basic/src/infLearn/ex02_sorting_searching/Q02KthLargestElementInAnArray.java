package infLearn.ex02_sorting_searching;

import java.util.Arrays;

public class Q02KthLargestElementInAnArray {
    public static void main(String[] args){
      Q02KthLargestElementInAnArray ex = new Q02KthLargestElementInAnArray();
      int[] input = {3,2,3,1,2,4,5,5,6};
      int k = 4;
      System.out.println(ex.solution(input, k));
    }
    
    public int solution(int[] input, int k) {
        Arrays.sort(input);
        return input[input.length - k];
    }
}
