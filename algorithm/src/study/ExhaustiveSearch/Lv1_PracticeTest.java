package study.ExhaustiveSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2021-01-16
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 완전탐색 > 모의고사
 * https://programmers.co.kr/learn/courses/30/lessons/42840
 */
public class Lv1_PracticeTest {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        System.out.println(Arrays.toString(solution(arr)));
    }

    public static int[] solution(int[] answers) {
        int[] person1 = {1,2,3,4,5};
        int[] person2 = {2,1,2,3,2,4,2,5};
        int[] person3 = {3,3,1,1,2,2,4,4,5,5};
        int answerCount1 = confirmAnswer(answers, person1);
        int answerCount2 = confirmAnswer(answers, person2);
        int answerCount3 = confirmAnswer(answers, person3);

        return checkWinner(answerCount1,answerCount2,answerCount3);
    }

    private static int[] checkWinner(int answerCount1, int answerCount2, int answerCount3) {
        ArrayList<Integer> winner = new ArrayList<>();
        if(answerCount1 >= answerCount2 && answerCount1 >= answerCount3){
            winner.add(1);
        }
        if(answerCount2 >= answerCount1 && answerCount2 >= answerCount3){
            winner.add(2);
        }
        if(answerCount3 >= answerCount1 && answerCount3 >= answerCount2){
            winner.add(3);
        }
        return winner.stream().mapToInt(i->i).toArray();
    }

    private static int confirmAnswer(int[] answers, int[] howSelect) {
        int result = 0;
        int arrayNumber = 0;
        for (int answer : answers) {
            if (answer == howSelect[arrayNumber]) {
                result++;
            }
            if (arrayNumber == howSelect.length - 1) {
                arrayNumber = 0;
            } else {
                arrayNumber++;
            }
        }
        return result;
    }
}
