package pf.tetris;

import javax.swing.*;

public class APIWaker {
    public APIWaker() {
        HighScores.getScores();
        javax.swing.Timer timer = new javax.swing.Timer(600000, e -> {
            HighScores.getScores();
        });
        timer.start();
    }
}
