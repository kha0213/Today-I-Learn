package codingTest.t230124;

import java.util.List;

public class Ex01 {
    public static void main(String[] args) {

    }
    public static int balancedSum(List<Integer> arr) {
        int sum = arr.stream()
                .mapToInt(Integer::intValue)
                .sum();
        int leftSum = 0;
        for (int i = 0; i < arr.size(); i++) {
            int tempSum = sum - arr.get(i);
            if (tempSum % 2 == 0) {
                if (tempSum / 2 == leftSum) {
                    return i;
                }
            }
            leftSum += arr.get(i);
        }
        return 0;
    }
}
