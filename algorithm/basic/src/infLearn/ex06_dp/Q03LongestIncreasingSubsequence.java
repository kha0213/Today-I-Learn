package infLearn.ex06_dp;

import java.util.Arrays;

public class Q03LongestIncreasingSubsequence {
    public static void main(String[] args){
        int[] input = {1,2,3,4,5,2,6,10,4,12};
        System.out.println("expected 8 : " + solution(input));
        int[] input2 = {0, 1, 0, 3, 2, 3};
        System.out.println("expected 4 : " + solution(input2));
        int[] input3 = {7, 7, 7, 7, 7, 7};
        System.out.println("expected 1 : " + solution(input3));
    }
    
    public static int solution(int[] nums) {
        int max = 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;

        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
