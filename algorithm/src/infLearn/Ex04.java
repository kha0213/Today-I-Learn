package infLearn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 *  Question 4 : [Daily Temperature]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Ex04 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] output = new int[temperatures.length];

        for(int i = 0; i < temperatures.length; i++) {
            if(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                output[stack.peek()] = i - stack.pop();
                i--;
                continue;
            }

            stack.push(i);
        }



        return output;
    }
}
