package pf.tetris;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private int delay;

    private JFrame frame;

    public Game(Board board) {
        this.delay = 10;
        JPanel panel = new JPanel();
        int[][] boardArray = board.getBoard();
        int width = boardArray[0].length;
        int height = boardArray.length;
        panel.setLayout(new GridLayout(height, width));
        for (int i = 0; i < height; i++) {
            for (int n = 0; n < width; n++) {
                panel.add(new JLabel(new ImageIcon(getTile(boardArray[i][n]).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
            }
        }
        panel.setSize(new Dimension(160, 320));
        frame = new JFrame("PabloTetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        Timer timer = new Timer(this.delay, e -> {
            int[][] oardArray = board.getBoard();
            panel.removeAll();
            for (int i = 0; i < height; i++) {
                for (int n = 0; n < width; n++) {
                    panel.add(new JLabel(new ImageIcon(getTile(oardArray[i][n]).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
                }
            }
            panel.revalidate();
            panel.repaint();
        });
        timer.start();
    }
//TODO: Averiguar porque la funcion refresh es inutil
    public void refresh(Board board){
        int[][] boardArray = board.getBoard();
        int width = boardArray[0].length;
        int height = boardArray.length;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(height, width));
        for (int i = 0; i < height; i++) {
            for (int n = 0; n < width; n++) {
                panel.add(new JLabel(new ImageIcon(getTile(boardArray[i][n]).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
            }
        }
        panel.setSize(new Dimension(160, 320));
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public ImageIcon getTile(int colour) {
        switch (colour) {
            case 0:
                return new ImageIcon("resources/tiles/Black_Tile.png");
            case 1:
                return new ImageIcon("resources/tiles/Blue_Tile.png");
            case 2:
                return new ImageIcon("resources/tiles/Cyan_Tile.png");
            case 3:
                return new ImageIcon("resources/tiles/Green_Tile.png");
            case 4:
                return new ImageIcon("resources/tiles/Orange_Tile.png");
            case 5:
                return new ImageIcon("resources/tiles/Purple_Tile.png");
            case 6:
                return new ImageIcon("resources/tiles/Red_Tile.png");
            case 7:
                return new ImageIcon("resources/tiles/Yellow_Tile.png");
            case 8:
                return new ImageIcon("resources/tiles/Pink_Tile.png");
            default:
                return new ImageIcon("resources/tiles/Black_Tile.png");
        }
    }
}
