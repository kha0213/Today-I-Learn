package study.etc;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Ex10757 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String[] num = a.split(" ");

        Stack<Integer> stack1 = new Stack<>();
        for (int i = 0; i < num[0].length(); i++) {
            stack1.push((num[0].charAt(i) - '0'));
        }
        Stack<Integer> stack2 = new Stack<>();
        for (int i = 0; i < num[1].length(); i++) {
            stack2.push((num[1].charAt(i) - '0'));
        }

        int before = 0;
        List<Integer> result = new ArrayList<>();
        while (!(stack1.isEmpty() && stack2.isEmpty())) {
            int v1 = stack1.isEmpty() ? 0 : stack1.pop();
            int v2 = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = before + v1 + v2;
            if (sum > 9) {
                before = 1;
                result.add(sum % 10);
            } else {
                before = 0;
                result.add(sum);
            }
        }
        if (before == 1) {
            result.add(1);
        }

        Collections.reverse(result);
        String out = result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println(out);
        sc.close();
    }
}


