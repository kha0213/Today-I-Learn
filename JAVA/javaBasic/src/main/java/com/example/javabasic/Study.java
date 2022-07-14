package com.example.javabasic;

public class Study {
    static class Parent {
        public void overload(int a) {
            System.out.println("overload a");
        }
        public void overload(int a, int b) {
            System.out.println("overload ab");
        }
        public void overload(String c) {
            System.out.println("overload c");
        }

        public void override(String a) {
            System.out.println("override a");
        }
    }

    static class Child extends Parent {
        @Override
        public void override(String a) {
            System.out.println("child override a");
        }
    }

}
