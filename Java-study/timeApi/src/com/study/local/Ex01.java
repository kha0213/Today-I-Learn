package com.study.local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

/**
 * Created by Kim Young Long.
 * Date : 2021-10-06.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Ex01 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2021, 10, 6);
        int year = date.getYear();              //2021
                   //date.get(ChronoField.DAY_OF_YEAR);
        Month month = date.getMonth();          //10월인 Month 객체
        int day = date.getDayOfMonth();         //6
        DayOfWeek dow = date.getDayOfWeek();    //WEDNESDAY
        int leng = date.lengthOfMonth();        //31 (해당 월은 31일 까지)
        boolean leap = date.isLeapYear();       //false (윤년여부)


    }
}
