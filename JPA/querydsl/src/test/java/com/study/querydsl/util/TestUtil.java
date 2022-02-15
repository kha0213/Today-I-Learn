package com.study.querydsl.util;

import java.util.List;

public class TestUtil {
    public static void print(Object obj) {
        System.out.println("obj = " + obj);
    }

    public static <T> void print(List<T> list) {
        for (T t : list) {
            System.out.println("list = " + t);
        }
    }
}
