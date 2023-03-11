package codingTest.t230225;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Ex02 {
    public static void main(String[] args) {

//        int leave = 3;
//        String day = "SUN";
//        int[] holidays = {2, 6, 17, 29};
        int leave = 4;
        String day = "FRI";
        int[] holidays = {6, 21, 23, 27, 28};
        System.out.println(new Ex02().solution(leave, day, holidays));

    }

    public int solution(int leave, String day, int[] holidays) {
        Set<Integer> holidaySet = Arrays.stream(holidays)
                .boxed()
                .collect(Collectors.toSet());
        int firstDayOfWeek = getDayOfWeek(day);

        Queue<Integer> queue = new LinkedList<>();
        int answer = 0;
        int useLeave = 0;

        int vacationStartDay = 1;
        for (int i = 1; i <= 30; i++) {
            if (isOffDay(i, holidaySet, firstDayOfWeek)) {
                continue;
            }

            if (useLeave < leave) {
                useLeave++;
                queue.add(i);
            } else if (useLeave == leave) {
                answer = Math.max(answer, i - vacationStartDay);
                int poll = queue.poll();
                vacationStartDay = poll + 1;
                queue.add(i);
            }
        }

        answer = Math.max(answer, 31 - vacationStartDay);
        return useLeave < leave ? 30 : answer;
    }

    private boolean isOffDay(int today, Set<Integer> holidays, int firstDayOfWeek) {
        if(holidays.contains(today)) {
            return true;
        }
        int day = (today + firstDayOfWeek) % 7;
        return day == 0 || day == 1;
    }

    public int getDayOfWeek(String day) {
        return switch (day) {
            case "MON" -> 1;
            case "TUE" -> 2;
            case "WED" -> 3;
            case "THU" -> 4;
            case "FRI" -> 5;
            case "SAT" -> 6;
            case "SUN" -> 7;
            default -> -1;
        };
    }
}
