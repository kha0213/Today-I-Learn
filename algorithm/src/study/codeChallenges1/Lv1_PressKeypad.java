package study.codeChallenges1;

/**
 * 2021-01-21
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 2020 카카오 개발자 인턴쉽 > 키패드 누르기
 * https://programmers.co.kr/learn/courses/30/lessons/67256
 */
public class Lv1_PressKeypad {
    public static void main(String[] args) {
        var pk = new Lv1_PressKeypad();
        int[] numbers = {1,3,4,5,8,2,1,4,5,9,5};
        System.out.println(pk.solution(numbers,"right"));

    }

    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        int[] left = new int[]{3,0};
        int[] right = new int[]{3,2};
        boolean isLeftHand = hand.equals("left");

        for (int num : numbers) {
            boolean leftWin = true;
            int number = num == 0 ? 10 : num - 1;
            int[] putNum = new int[]{number / 3, number % 3};
            if (number % 3 == 1) {
                int leftDistance = Math.abs(left[0] - putNum[0]) + Math.abs(left[1] - putNum[1]);
                int rightDistance = Math.abs(right[0] - putNum[0]) + Math.abs(right[1] - putNum[1]);
                leftWin = leftDistance < rightDistance ||
                        (leftDistance == rightDistance && isLeftHand);
            } else if (number % 3 == 2) {
                leftWin = false;
            }

            if (leftWin) { // 왼쪽 승
                left[0] = putNum[0];
                left[1] = putNum[1];
                answer.append("L");
            } else {
                right[0] = putNum[0];
                right[1] = putNum[1];
                answer.append("R");
            }
        }
        return answer.toString();
    }

}
