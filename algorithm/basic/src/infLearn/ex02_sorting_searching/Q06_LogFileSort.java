package infLearn.ex02_sorting_searching;

import java.util.*;

public class Q06_LogFileSort {
    public static void main(String[] args){
      Q06_LogFileSort ex = new Q06_LogFileSort();
      String[] input = {
                "dig1 8 2 3 1",
                "let1 abc cat",
                "dig1 2 5",
                "let2 good dog book",
                "let3 abc zoo"
      };
      System.out.println(Arrays.toString(ex.solution(input)));
    }
    
    public String[] solution(String[] input) {
        Queue<String> letQueue = new PriorityQueue<>(logComparator());
        List<String> digList = new ArrayList<>();
        for (String s : input) {

        }
        return null;
    }

    static Comparator<String> logComparator() {
        return new Comparator<>() {
            @Override
            public int compare(String o1, String o2) {
                String[] o1Arr = {o1.substring(0,o1.indexOf(" ")), o1.substring(o1.indexOf(" ")+1)};
                String[] o2Arr = {o2.substring(0,o2.indexOf(" ")), o2.substring(o2.indexOf(" ")+1)};
                return o1.compareTo(o2);
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        };
    }
}
