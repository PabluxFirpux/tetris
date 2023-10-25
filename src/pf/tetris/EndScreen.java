package pf.tetris;

import javax.swing.*;

public class EndScreen extends JFrame {
    private int score;

    private String name;
    public EndScreen (int score) {
        super("PabloTetris");
        this.score = score;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JLabel gameOverLabel = new JLabel("Game Over!");
        add(gameOverLabel);
        JLabel scoreLabel = new JLabel("Your score was: " + score);
        add(scoreLabel);
        JButton retryButton = new JButton("Retry");
        retryButton.setAlignmentX(CENTER_ALIGNMENT);
        add(retryButton);
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        add(backButton);
        JButton quitButton = new JButton("Quit");
        quitButton.setAlignmentX(CENTER_ALIGNMENT);
        add(quitButton);
        setIconImage(new ImageIcon("resources/images/GAMEOVER.jpg").getImage());


       backButton.addActionListener(e -> {
            dispose();
            MainScreen newMainScreen = new MainScreen();
        });

        retryButton.addActionListener(e -> {
            dispose();
            Controller newController = new Controller();
        });

        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        setVisible(true);
        checkIfHighScore();

    }

    public void checkIfHighScore() {
        if (score > HighScores.getLowestScore()) {
            name = JOptionPane.showInputDialog("You got a high score! Enter your name:");
            HighScores.addScore(name, score);
        }
    }
}
