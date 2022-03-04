package infLearn.ex06_dp;

import java.util.Arrays;

public class Q02_coinChange {
    public static void main(String[] args){
      Q02_coinChange ex = new Q02_coinChange();
        int[] coins = new int[]{1, 2, 6, 7, 8};
      int amount = 13;
      System.out.println(ex.solution(coins, amount));
    }
    
    public int solution(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i < max; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        System.out.println("===================");
        System.out.println(Arrays.toString(dp));

        return Math.min(dp[amount], amount);
    }
}
