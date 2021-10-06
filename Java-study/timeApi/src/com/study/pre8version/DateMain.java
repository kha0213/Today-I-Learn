package com.study.pre8version;

import java.util.Date;

/**
 * Created by Kim Young Long.
 * Date : 2021-10-06.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class DateMain {
    public static void main(String[] args) {
        Date date2 = new Date(122, 9, 6);
        Date date1 = new Date(121, 9, 7);
        long betweenTime = date2.getTime() - date1.getTime();
        int betweenDay = (int) (betweenTime / (1000 * 60 * 60 * 24));
        System.out.println(betweenDay);


    }
}
