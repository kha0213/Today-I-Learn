package com.study.local;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 * Created by Kim Young Long.
 * Date : 2021-10-06.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Ex01_Initialization {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2021, 10, 6);
        LocalTime now = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.parse("2021-10-06 11:12:13",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println("dateTime = " + dateTime);

    }
}
