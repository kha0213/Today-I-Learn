package com.example.javabasic.override;

import org.junit.jupiter.api.Test;

public class OverloadingTest {
    static class Overload {
        public static void print(Object o) {
            System.out.println("this is Object parameter");
        }
        public static void print(String s) {
            System.out.println("this is String parameter");
        }
    }

    @Test
    void overloadingTest() {
        Object a = "string";
        Overload.print(a); // this is Object parameter
    }

}
