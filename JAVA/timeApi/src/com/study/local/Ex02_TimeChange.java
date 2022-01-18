package com.study.local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;

/**
 * Created by Kim Young Long.
 * Date : 2021-10-07.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Ex02_TimeChange {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime newDateTime = dateTime.withYear(2020);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        date.with(ChronoField.DAY_OF_MONTH, 3);
        date.plus(3, ChronoUnit.MONTHS);
        System.out.println("dateTime = " + dateTime);
    }
}
