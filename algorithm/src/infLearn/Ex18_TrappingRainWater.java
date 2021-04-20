package infLearn;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Question 18 : [Trapping Rain Water]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-20.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Ex18_TrappingRainWater {
    public static void main(String[] args) {
        int[] list = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trappingRainWater(list));
    }
    public static int trappingRainWater(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];

        int max = 0;

        for(int i = 0; i < height.length; i++) {
            if(max < height[i]) {
                max = height[i];
            }
            left[i] = max;
        }

        max = height[height.length - 1];
        for(int i = height.length-1; i >= 0; i--) {
            if(max < height[i]) {
                max = height[i];
            }
            right[i] = max;
        }

        int[] water = new int[height.length];
        for(int i = 0; i < height.length; i++) {
            water[i] = Math.min(left[i], right[i]) - height[i];
        }

        return Arrays.stream(water).sum();
    }
}
