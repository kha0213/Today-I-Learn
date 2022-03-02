package infLearn.ex03_Array;

import java.util.Stack;

public class Q02_MaximunSubArray {
    public static void main(String[] args) {
        Q02_MaximunSubArray ex = new Q02_MaximunSubArray();
        int[] input = {-10,6,-2,1,-3,4,-1,2,1,-5,4};
        int[] input2 = {10,10,-3,10,10};
        System.out.println(ex.solution(input));
        System.out.println(ex.solution(input2));
    }
    
    public int solution(int[] input) {
        int maxSum = 0, tempSum = 0;

        for (int i = 0; i < input.length; i++) {
            tempSum = Math.max(input[i], input[i] + tempSum);
            maxSum = Math.max(maxSum, tempSum);
        }
        return maxSum;
    }
}
