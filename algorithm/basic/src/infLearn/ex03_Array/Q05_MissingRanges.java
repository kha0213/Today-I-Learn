package infLearn.ex03_Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q05_MissingRanges {
    public static void main(String[] args) {
        int[] nums = {2, 3, 50, 5, 75};
        int lower = 0, upper = 99;
        //Output: [0->1, 4, 6->49, 51->74, 76->99]
        System.out.println(Arrays.toString(solution2(nums, lower, upper)));
        int[] nums2 = {};
        int lower2 = 1, upper2 = 1;
        //Output: [0->1, 4, 6->49, 51->74, 76->99]
        System.out.println(Arrays.toString(solution2(nums2, lower2, upper2)));
    }

    public static String[] solution(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int min = lower - 1;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            String missingRange = getMissingRange(nums[i], min);
            if (missingRange != null) {
                result.add(missingRange);
            }
            min = nums[i];
        }
        String last = getMissingRange(upper + 1, min);
        if (last != null) {
            result.add(last);
        }
        return result.toArray(new String[0]);
    }

    private static String getMissingRange(int curr, int min) {
        if (curr - 1 == min) {
            return null;
        } else if (curr - 2 == min) {
            return String.valueOf(curr - 1);
        }
        return ( min + 1 ) + "->" + (curr - 1) ;
    }

    public static String[] solution2(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        List<String> result = new ArrayList<>();
        if (nums.length == 0) return new String[]{makeRange(lower,upper)};
        if (lower < nums[0]) {
            result.add(makeRange(lower, nums[0] - 1));
        }
        //2, 3, 5, 50, 75;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 < nums[i + 1]) {
                result.add(makeRange(nums[i] + 1, nums[i + 1] - 1));
            }
        }

        if (nums[nums.length - 1] < upper) {
            result.add(makeRange(nums[nums.length - 1] + 1, upper));
        }


        return result.toArray(new String[0]);
    }

    private static String makeRange(int lower, int higher) {
        return lower == higher ? String.valueOf(lower) : lower + "->" + higher;
    }
}