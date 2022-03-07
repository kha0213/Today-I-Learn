package study.dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2839
 * 설탕 배달
 */
public class Ex2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(solution(N));
    }

    // dp[n] = Math.min(dp[n-3], dp[n-5]) + 1

    private static int solution(int n) {
        if( n < 3 || n == 4 ) return -1;
        if( n == 3 || n == 5 ) return 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 5000);
        dp[3] = 1;
        dp[5] = 1;
        for (int i = 6; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
        }
        return dp[n] >= 5000 ? -1 : dp[n];
    }
}
