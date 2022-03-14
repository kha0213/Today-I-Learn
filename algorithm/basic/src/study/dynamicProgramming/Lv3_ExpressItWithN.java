package study.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lv3_ExpressItWithN {
    public static void main(String[] args){
      Lv3_ExpressItWithN ex = new Lv3_ExpressItWithN();
        int N = 5, number = 12;
        int N2 = 2, number2 = 11;
        System.out.println("expect 4 : " + ex.solution(N, number));
        System.out.println("expect 3 : " + ex.solution(N2, number2));
    }

    public int solution(int N, int number) {
        List<Set<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(new HashSet<>());
        }
        list.get(1).add(N);
        for (int i = 2; i < 9; i++) {
            Set<Integer> set = list.get(i);
            set.add( ((int) Math.pow(10, i) - 1) / 9 * N);
            for (int j = 1; j < i; j++) {
                Set<Integer> set1 = list.get(j);
                Set<Integer> set2 = list.get(i - j);
                for (Integer num1 : set1) {
                    for (Integer num2 : set2) {
                        set.add(num1 + num2);
                        set.add(num1 - num2);
                        set.add(num1 * num2);
                        if(num2 != 0)
                            set.add(num1 / num2);
                    }
                }
            }
        }

        for (int i = 1; i < list.size(); i++) {
            if(list.get(i).contains(number))
                return i;
        }
        return -1;
    }
}
