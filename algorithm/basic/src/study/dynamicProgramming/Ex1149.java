package study.dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class Ex1149 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[][] maps = new int[N][3];
        for (int i = 0; i < N; i++) {
            maps[i] = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(solution(maps));
    }

    private static int solution(int[][] maps) {
        if(maps.length == 1) return Arrays.stream(maps[0]).min().getAsInt();
        int[][] dp = new int[maps.length][3];
        dp[0] = maps[0];
        for (int i = 1; i < maps.length; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + maps[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + maps[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + maps[i][2];
        }
        return Arrays.stream(dp[dp.length - 1]).min().getAsInt();
    }
}
