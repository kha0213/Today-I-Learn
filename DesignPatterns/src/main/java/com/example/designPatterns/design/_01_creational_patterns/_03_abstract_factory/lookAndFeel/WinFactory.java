package com.example.design._01_creational_patterns._03_abstract_factory.lookAndFeel;

public class WinFactory implements IGUIFactory {
    @Override
    public IButton createButton() {
        return new WinButton();
    }
}
