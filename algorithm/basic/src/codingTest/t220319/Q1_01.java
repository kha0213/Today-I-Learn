package codingTest.t220319;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1_01 {

    public static void main(String[] args) throws Exception {
        Q1_01 main = new Q1_01();
        int[] a = {1,2,2,3,3,3};
        int[] b = {2,3,3,4,5};
        int[] result = main.solution(a,b);

        System.out.println(Arrays.toString(result));
    }

    // 아래 메서드의 내용을 수정 작성하여 완성해 주세요.
    public int[] solution(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        List<Integer> list = new ArrayList<>();

        int idx = 0;
        for (int i = 0; i < b.length; i++) {
            for (int j = idx; j < a.length; j++) {
                if (b[i] == a[j]) {
                    list.add(b[i]);
                    idx = j + 1;
                    break;
                } else if (b[i] < a[j]) {
                    idx = j;
                    break;
                }
            }
        }

        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}
