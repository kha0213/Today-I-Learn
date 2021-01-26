package study.exhaustiveSearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 2021-01-26
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 완전탐색 > 소수 찾기
 * https://programmers.co.kr/learn/courses/30/lessons/42839
 */
public class Lv2_SearchPrime {
    public static void main(String[] args) {
        Lv2_SearchPrime sp = new Lv2_SearchPrime();
        System.out.println(sp.solution("17"));
    }
    public int solution(String numbers){
        String[] numArr = numbers.split("");
        Set<Integer> numSet = new HashSet<>();
        boolean[] visited = new boolean[numArr.length];
        String[] output = new String[numArr.length];
        for( int i = 1; i <= numArr.length; i++ ) {
            recursion(numArr, output ,numSet, visited,0,i);
        }

        return (int) numSet.stream().filter(i -> {
            if(i == 0 || i == 1) return true;
            else if(i == 2) return false;
            for(int j=2; j*j <= i; j++) {
                if(i%j==0) return false;
            }
            return true;
        }).count();
    }
    private void recursion(String[] numArr, String[] output, Set<Integer> numSet, boolean[] visited, int depth, int r) {
        int length = numArr.length;
        if(r == depth) {
            numSet.add(Integer.parseInt(Arrays.stream(output).filter(Objects::nonNull).collect(Collectors.joining())));
            return;
        }

        for(int i=0; i < length; i++) {
            if(!visited[i]){
                visited[i] = true;
                output[i] = numArr[i];
                recursion(numArr, output, numSet, visited, depth+1, r);
                visited[i] = false;
            }
        }

    }
}
