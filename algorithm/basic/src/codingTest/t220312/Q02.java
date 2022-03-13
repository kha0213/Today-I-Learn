package codingTest.t220312;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Q02 {
    public static void main(String[] args) {
        Q02 ex = new Q02();
//        int[] input = new int[]{3,3,6,7,7,9};
//        System.out.println( "12 : " +ex.solution(input));
        int[] input2 = new int[]{2,2,3,3,8,8};
        System.out.println( "13 : " +ex.solution(input2));
//        int[] input3 = new int[]{5,5,5,5,5};
//        System.out.println( "6 : " +ex.solution(input3));
    }

    int length = 0; // 바닥 길이
    public int solution(int[] input) {
        length = 1000000;
        if(input.length < 3) return 0;
        List<Integer> nums = Arrays.stream(input).boxed().sorted((i, j) -> j - i).collect(toList());

        int high = 0; // 높이


        int startIdx = 0; // 적용한 idx

        for (int i = 2; i < nums.size(); i++) {
            int built = built(nums, startIdx, i);
            if (built != 0) {
                startIdx = i + 1;
                high += built;
            }
        }
        return high;
    }

    private int built(List<Integer> nums, int startIdx, int endIdx) {
        List<Integer> list = nums.subList(startIdx, endIdx+1);
        if(list.size() < 3) return 0;
        int left = 0;
        int exclude = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == list.get(i - 1)) {
                left = list.get(i);
                exclude = i;
                break;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (i != exclude && i != exclude - 1 && list.get(i) <= length) {
                length = list.get(i);
                return left + 1;
            }
        }
        return 0;
    }
}
