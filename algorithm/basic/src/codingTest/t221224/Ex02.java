package codingTest.t221224;

import java.util.stream.IntStream;

public class Ex02 {
    public static void main(String[] args) {
//        System.out.println( new Ex02().solution(new int[]{-9,-15,-11,22}));
        System.out.println( new Ex02().solution(new int[]{1800,1800,1200,2400})); //-> 60
//        System.out.println( new Ex02().solution(new int[]{1,2,3,5}));
//        System.out.println( new Ex02().solution(new int[]{-1,-2,1,2}));
//        System.out.println( new Ex02().solution(new int[]{-5,-3,12,24}));
//        System.out.println( new Ex02().solution(new int[]{-50,50}));
    }

    int key;
    int key2;
    int min = Integer.MAX_VALUE;
    public int solution(int[] numbers) {
        if(numbers.length == 2)
            return Math.abs(numbers[0] - numbers[1]);
        int sum = IntStream.of(numbers).sum();

        key = sum / 2;
        if (sum % 2 == 0) {
            dfsEven(numbers, 0, 0, false);
            return min * 2;
        }

        if (sum < 0) {
            key2 = key - 1;
        } else {
            key2 = key + 1;
        }

        dfsOdd(numbers, 0, 0, false);
        return min * 2 + 1;
    }
    private void dfsOdd(int[] numbers, int sum, int idx, boolean visited) {
        if(idx == numbers.length)
            return;
        if((sum == key || sum == key2) && visited) {
            min = 0;
            return;
        }
        if (visited) {
            int abs = Math.min(Math.abs(key - sum), Math.abs(key2 - sum));
            min = Math.min(min, abs);
        }
        dfsOdd(numbers, sum+numbers[idx], idx+1, true);
        dfsOdd(numbers, sum, idx+1, visited);
    }
    private void dfsEven(int[] numbers, int sum, int idx, boolean visited) {
        if(idx == numbers.length)
            return;
        if(sum == key && visited) {
            min = 0;
            return;
        }
        if (visited)
            min = Math.min(min, Math.abs(key - sum));
        dfsEven(numbers, sum+numbers[idx], idx+1, true);
        dfsEven(numbers, sum, idx+1, visited);
    }
}
