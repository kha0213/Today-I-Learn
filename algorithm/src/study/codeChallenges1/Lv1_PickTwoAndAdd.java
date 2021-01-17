package study.codeChallenges1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * 2021-01-17
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 월간 코드 챌린지 시즌1 > 두 개 뽑아서 더하기
 * https://programmers.co.kr/learn/courses/30/lessons/68644
 */
public class Lv1_PickTwoAndAdd {
    public static void main(String[] args) {
        int[] numbers = {5,0,2,7};
        solution(numbers);
    }

    public static int[] solution(int[] numbers) {
        int size = numbers.length;
        return  IntStream.range(0, size - 1).flatMap(a ->
                IntStream.range(a + 1, size).map(b -> numbers[b]+numbers[a])
        ).distinct().sorted().toArray();
    }
}
