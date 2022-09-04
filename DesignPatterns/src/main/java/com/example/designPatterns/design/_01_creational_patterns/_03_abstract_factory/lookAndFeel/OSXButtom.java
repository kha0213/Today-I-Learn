package com.example.design._01_creational_patterns._03_abstract_factory.lookAndFeel;

public class OSXButtom implements IButton {
    @Override
    public void paint() {
        System.out.println("OSXButtom paint!!!");
    }
}
