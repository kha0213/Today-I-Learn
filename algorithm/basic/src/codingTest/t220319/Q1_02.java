package codingTest.t220319;

import java.util.Arrays;

public class Q1_02 {
    // 아래 메서드의 내용을 수정 작성하여 완성해 주세요.
    public double solution(int l, int[] a) {
        Arrays.sort(a);
        int max = a[0] * 2;
        for (int i = 1; i < a.length; i++) {
            max = Math.max(max, a[i] - a[i-1]);
        }
        max = Math.max(max, (l - a[a.length - 1]) * 2);
        return (double)max / 2;
    }


    public static void main(String[] args) throws Exception {
        Q1_02 main = new Q1_02();

        int l = 10;
        int[] a = {2, 5};

        double result = main.solution(l, a);

        System.out.println(result);
    }
}
