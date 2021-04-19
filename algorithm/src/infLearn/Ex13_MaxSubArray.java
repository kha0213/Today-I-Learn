package infLearn;

/**
 *
 * Question 13 : [Max Sub Array]
 *
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-04-19
 * Time: 오후 8:58
 */
public class Ex13_MaxSubArray {
    public static void main(String[] args) {
        int[] nums ={-2,1,-3,4,-1,2,1,-5,4};
        final int maxSubArray = new Ex13_MaxSubArray().maxSubArray(nums);
        System.out.println("maxSubArray = " + maxSubArray);
    }

    public int maxSubArray(int[] nums) {

        int maxSum = 0;
        int tempSum = 0;

        for (int n : nums) {
            tempSum = Math.max(n, tempSum + n);
            maxSum = Math.max(maxSum, tempSum);
        }

        return maxSum;
    }
}
