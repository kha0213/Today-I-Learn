package study.dynamicProgramming;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1003
 */
public class Ex1003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            System.out.println(solution(sc.nextInt()));
        }
    }

    // dp[n][0] = dp[n-1][0] + dp[n-2][0]
    private static String solution(int n) {
        if(n == 0) return "1 0";
        if(n == 1) return "0 1";
        int[][] dp = new int[n + 1][2];
        dp[0] = new int[]{1, 0};
        dp[1] = new int[]{0, 1};

        for (int i = 2; i < n+1; i++) {
            dp[i] = new int[]{dp[i - 1][0] + dp[i - 2][0], dp[i - 1][1] + dp[i - 2][1]};
        }

        return dp[n][0] + " " + dp[n][1];
    }
}
