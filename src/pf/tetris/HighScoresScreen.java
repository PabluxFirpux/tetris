package pf.tetris;

import javax.swing.*;

public class HighScoresScreen extends JFrame {
    public HighScoresScreen() {
        super("PabloTetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JLabel highScoresLabel = new JLabel("High Scores:");
        add(highScoresLabel);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        add(backButton);
        JButton quitButton = new JButton("Quit");
        quitButton.setAlignmentX(CENTER_ALIGNMENT);
        add(quitButton);

        backButton.addActionListener(e -> {
            dispose();
            MainScreen newMainScreen = new MainScreen();
        });

        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        setVisible(true);

    }
}
