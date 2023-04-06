package study.programmers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class Lv2_176962 {
    public static void main(String[] args) {
        Lv2_176962 lv2_176962 = new Lv2_176962();
        String[][] plans = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        // ["korean", "english", "math"]
        System.out.println(Arrays.toString(lv2_176962.solution(plans)));
    }
    public String[] solution(String[][] plans) {
        List<Plan> list = Arrays.stream(plans)
                .map(plan -> new Plan(plan[0], plan[1], plan[2]))
                .sorted((a, b) -> a.start.compareTo(b.start))
                .toList();
        List<String> answer = new ArrayList<>();
        Stack<Plan> stack = new Stack<>();

        Plan before = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Plan current = list.get(i);
            int time = current.start.getMinute() - before.start.getMinute();

            if ( time < before.min ) {
                stack.add(new Plan(before.name, before.start, before.min - time));
            } else {
                time -= before.min;
                answer.add(before.name);

                while(!stack.isEmpty() && time > 0) {
                    Plan pop = stack.pop();
                    if ( time < pop.min ) {
                        stack.add(new Plan(pop.name, pop.start, pop.min - time));
                        break;
                    } else {
                        time -= pop.min;
                        answer.add(pop.name);
                    }
                }
            }
            before = current;
        }

        return answer.toArray(new String[0]);
    }

    static class Plan {
        String name;
        LocalTime start;
        int min;

        public Plan(String name, String start, String min) {
            this.name = name;
            this.start = LocalTime.parse(start, DateTimeFormatter.ofPattern("HH:mm"));
            this.min = Integer.parseInt(min);
        }

        public Plan(String name, LocalTime start, int min) {
            this.name = name;
            this.start = start;
            this.min = min;
        }
    }
}
