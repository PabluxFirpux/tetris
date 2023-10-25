package pf.tetris;

import javax.swing.*;

public class MainScreen extends JFrame {
    public MainScreen() {
        super("PabloTetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(CENTER_ALIGNMENT);
        add(startButton);
        JButton HSButton = new JButton("High Scores");
        HSButton.setAlignmentX(CENTER_ALIGNMENT);
        add(HSButton);
        JButton quitButton = new JButton("Quit");
        quitButton.setAlignmentX(CENTER_ALIGNMENT);
        add(quitButton);

        startButton.addActionListener(e -> {
            dispose();
            Controller newController = new Controller();
        });

        HSButton.addActionListener(e -> {
            dispose();
            HighScoresScreen newHighScoresScreen = new HighScoresScreen();
        });

        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        setVisible(true);
    }
}
