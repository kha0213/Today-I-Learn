package study.dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1463
 */
public class Ex1463 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(solution(N));
    }

    private static int solution(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 5000);
        dp[1] = 0;
        for (int i = 1; i < n + 1; i++) {
            if (i + 1 <= n) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
            }
            if (i * 2 <= n) {
                dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
            }
            if (i * 3 <= n) {
                dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
            }
        }
        return dp[n];
    }
}
