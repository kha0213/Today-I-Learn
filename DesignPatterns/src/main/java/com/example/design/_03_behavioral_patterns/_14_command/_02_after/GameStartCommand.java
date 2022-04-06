package com.example.design._03_behavioral_patterns._14_command._02_after;

import com.example.design._03_behavioral_patterns._14_command._01_before.Game;
import com.example.design._03_behavioral_patterns._14_command._01_before.Light;

public class GameStartCommand implements Command {

    private Game game;

    public GameStartCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.start();
    }

    @Override
    public void undo() {
        new GameEndCommand(this.game).execute();
    }
}
