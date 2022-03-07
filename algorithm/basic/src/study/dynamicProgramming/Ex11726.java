package study.dynamicProgramming;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/11726
 */
public class Ex11726 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(solution(N));
    }

    private static int solution(int n) {
        if(n == 1 || n == 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < dp.length; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }
        return dp[n] % 10007;
    }
}
