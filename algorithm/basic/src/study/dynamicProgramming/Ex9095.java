package study.dynamicProgramming;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/9095
 * 1,2,3 더하기
 */
public class Ex9095 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            System.out.println(solution(sc.nextInt()));
        }
    }

    // dp[n] = dp[n-1] + dp[n-2] + dp[n-3]

    private static int solution(int n) {
        if(n == 1 || n == 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

}
