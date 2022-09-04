package com.example.design._01_creational_patterns._03_abstract_factory.lookAndFeel;

public class WinButton implements IButton {
    @Override
    public void paint() {
        System.out.println("WinButton paint!!");
    }
}
