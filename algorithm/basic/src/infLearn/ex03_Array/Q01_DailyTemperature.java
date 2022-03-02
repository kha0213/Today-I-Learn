package infLearn.ex03_Array;

import java.util.Arrays;
import java.util.Stack;

public class Q01_DailyTemperature {
    public static void main(String[] args){
      Q01_DailyTemperature ex = new Q01_DailyTemperature();
        int[] input = {73,74,75,71,69,72,76,73};
        int[] input2 = {30,40,50,60};
        int[] input3 = {10, 9, 8, 20, 18, 14, 30, 36};
        System.out.println(Arrays.toString(ex.solution(input)));
        System.out.println(Arrays.toString(ex.solution(input2)));
        System.out.println(Arrays.toString(ex.solution(input3)));
    }
    
    public int[] solution(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<int[]> stack = new Stack<>();
        for (int i=0; i<temperatures.length; i++) {
            if (stack.isEmpty()) {
                stack.push(new int[]{temperatures[i], i});
            } else {
                while (!stack.isEmpty() && stack.peek()[0] < temperatures[i]) {
                    int[] pop = stack.pop();
                    result[pop[1]] = i - pop[1];
                }
                stack.push(new int[]{temperatures[i], i});
            }
        }
        return result;
    }
}
