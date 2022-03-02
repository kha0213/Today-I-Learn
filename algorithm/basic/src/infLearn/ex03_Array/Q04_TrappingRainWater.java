package infLearn.ex03_Array;

import java.util.Arrays;

public class Q04_TrappingRainWater {
    public static void main(String[] args){
      Q04_TrappingRainWater ex = new Q04_TrappingRainWater();
        int[] input = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
      System.out.println(ex.solution(input));
    }
    
    public int solution(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        int leftMax=0, rightMax=0;
        for (int i = 0; i < height.length; i++) {
            leftMax = Math.max(leftMax, height[i]);
            left[i] = Math.max(leftMax, height[i]);
        }

        for (int i = height.length - 1; i >= 0; i--) {
            rightMax = Math.max(rightMax, height[i]);
            right[i] = Math.max(rightMax, height[i]);
        }
        
        int result = 0;

        for (int i = 0; i < height.length; i++) {
            result += Math.min(left[i], right[i]) - height[i];
        }

        return result;
    }
}
