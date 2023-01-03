package study.programmers;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class TTest {

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.parse("2022-12-01 09:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime end = LocalDateTime.parse("2022-12-05 15:25:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(new TTest().getBetweenDateStrd(start,end));
    }

    public String getBetweenDateStrd(LocalDateTime start, LocalDateTime end) {
        long seconds = getBetweenSecond(start, end);
        long hours = seconds / 3600;
        seconds -= hours * 3600;
        long minutes = seconds / 60;
        seconds -= minutes * 60;
        return hours + "시간 " + minutes + "분 " + seconds + "초";

    }
    
    public long getBetweenSecond(LocalDateTime start, LocalDateTime end) {
        LocalDate startDt = start.toLocalDate().plusDays(1);
        LocalDate endDt = end.toLocalDate();

        long second = Stream.iterate(startDt, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(startDt, endDt))
                .filter(date -> !date.getDayOfWeek().equals(SATURDAY))
                .filter(date -> !date.getDayOfWeek().equals(SUNDAY))
                .mapToInt(date -> 9 * 60 * 60)
                .sum();

        LocalTime workStart = LocalTime.of(9, 0, 0);
        LocalTime workEnd = LocalTime.of(18, 0, 0);
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();

        if (startTime.isAfter(workStart) && startTime.isBefore(workEnd)) {
            Duration firstWorkTime = Duration.between(startTime, workEnd);
            second += firstWorkTime.getSeconds();
        }

        if (endTime.isAfter(workStart) && endTime.isBefore(workEnd)) {
            Duration lastWorkTime = Duration.between(workStart, endTime);
            second += lastWorkTime.getSeconds();
        }

        return second;
    }
}
