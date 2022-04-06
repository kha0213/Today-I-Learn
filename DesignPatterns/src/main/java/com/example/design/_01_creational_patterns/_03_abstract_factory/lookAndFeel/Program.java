package com.example.design._01_creational_patterns._03_abstract_factory.lookAndFeel;

public class Program {
    public static void main(String[] args) {
        IGUIFactory factory = new WinFactory();

        // 부속품 만드는 메서드는 추상화되어 클라이언트는 구체 클래스를 몰라도 가능하다.
        IButton button = factory.createButton();
        button.paint();
    }
}
