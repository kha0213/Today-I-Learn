package codingTest.t230124;

import java.util.List;

public class Ex02 {
    public static void main(String[] args) {

    }

    /*
     * Complete the 'isPossible' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER a
     *  2. INTEGER b
     *  3. INTEGER c
     *  4. INTEGER d
     *
     * Yes, No
     */
    static boolean result;
    public static String isPossible(int a, int b, int c, int d) {
        dfs(a,b,c,d);
        return result ? "Yes" : "No";
    }

    private static void dfs(int a, int b, int c, int d) {
        if (a > c || b > d) {
            return;
        }
        if (a == c && b == d) {
            result = true;
            return;
        }
        dfs(a + b, b, c, d);
        dfs(a, a + b, c, d);
    }
}
