package infLearn;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *  Question 3 : [TwoSum]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Ex03_TwoSum {
    public static void main(String[] args) {
        twoSum(new int[]{2, 8, 11, 14}, 16);
        System.out.println(twoSum(new int[]{2, 8, 11, 14}, 16)[0]);
        System.out.println(twoSum(new int[]{2, 8, 11, 14}, 16)[1]);

    }
    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                return new int[]{i+1, map.get(nums[i])};
            } else {
                map.put(target - nums[i], i+1);
            }
        }
        return null;

    }

}
