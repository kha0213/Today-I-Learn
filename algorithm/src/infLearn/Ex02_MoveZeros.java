package infLearn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *  Question 2 : [MoveZeros]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Ex02_MoveZeros {
    public static void moveZeroes(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int n : nums) {
            if(n != 0) list.add(n);
        }
        while (list.size() < nums.length) {
            list.add(0);
        }
        System.out.println("list = " + list);
    }

    public static void main(String[] args) {
        int[] nums = {0, 3, 2, 0, 8, 5};
        moveZeroes(nums);
    }
}
