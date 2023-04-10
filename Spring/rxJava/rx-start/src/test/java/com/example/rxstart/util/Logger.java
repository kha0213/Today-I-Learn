package com.example.rxstart.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static void log(Object msg) {
        System.out.println(
                Thread.currentThread().getName() + " | "
                        + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")) + " | "
                        + msg);
    }
}
