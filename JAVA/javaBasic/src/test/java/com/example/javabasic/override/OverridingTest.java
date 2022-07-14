package com.example.javabasic.override;

import org.junit.jupiter.api.Test;

public class OverridingTest {
    static class Parent {
        public void print() {
            System.out.println("Parent print!!");
        }
    }

    static class Child extends Parent {
        @Override
        public void print() {
            System.out.println("Child print!!");
        }
    }

    @Test
    void overridingTest() {
        Parent a = new Child();
        a.print(); // Child print!!
    }

}
