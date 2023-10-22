package pf.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener {
    private int delay;

    private JFrame frame;

    private Controller controller;

    public Game(Board board, Controller controller) {
        this.controller = controller;
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
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new BorderLayout());
        JPanel background = new JPanel();
        background.setForeground(Color.BLACK);
        frame.add(background);
        background.setLayout(new BorderLayout());


        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        JPanel gameBuffer = new JPanel();
        gameBuffer.setLayout(new GridBagLayout());
        gameBuffer.setBackground(Color.BLACK);
        gameBuffer.add(panel);

        background.add(gameBuffer, BorderLayout.CENTER);
        //frame.getContentPane().setForeground(Color.BLACK);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLACK);
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLACK);
        JPanel rightPanel = new JPanel();
        rightPanel.setForeground(Color.BLACK);

        JPanel topRightPanel = new JPanel();
        JPanel lowRightPanel = new JPanel();
        JPanel nextBrickPanel = new JPanel();

        int[][] nextShape = controller.getNextBrick().getShape();
        topRightPanel.setLayout(new GridLayout(nextShape.length,nextShape[0].length));
        for (int[] row : nextShape) {
            for (int cell : row) {
                if (cell == 1) {
                    topRightPanel.add(new JLabel(new ImageIcon(getTile(controller.getNextBrick().getColour()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
                } else {
                    topRightPanel.add(new JLabel(new ImageIcon(getTile(0).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
                }
            }
        }

        topRightPanel.setSize(new Dimension(nextShape.length*30, nextShape[0].length*30));
        topRightPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        nextBrickPanel.setLayout(new GridBagLayout());
        nextBrickPanel.add(new JLabel("Next brick:"));
        nextBrickPanel.add(topRightPanel);
        nextBrickPanel.setBackground(Color.BLACK);

        JLabel scoreLabel = new JLabel("Score: " + controller.getScore());
        scoreLabel.setBackground(Color.WHITE);
        scoreLabel.setForeground(Color.WHITE);
        lowRightPanel.add(scoreLabel);
        lowRightPanel.setBackground(Color.BLACK);

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(nextBrickPanel);
        rightPanel.add(lowRightPanel);
        rightPanel.setBackground(Color.BLACK);

        background.add(topPanel, BorderLayout.NORTH);
        background.add(bottomPanel, BorderLayout.SOUTH);
        background.add(leftPanel, BorderLayout.WEST);
        background.add(rightPanel, BorderLayout.EAST);

        //frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        //GUI Updater
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

            lowRightPanel.removeAll();
            lowRightPanel.add(new JLabel("Score: " + controller.getScore()));
            lowRightPanel.revalidate();
            lowRightPanel.repaint();

            int[][] extShape = controller.getNextBrick().getShape();
            topRightPanel.removeAll();
            topRightPanel.setLayout(new GridLayout(extShape.length,extShape[0].length));
            for (int[] row : extShape) {
                for (int cell : row) {
                    if (cell == 1) {
                        topRightPanel.add(new JLabel(new ImageIcon(getTile(controller.getNextBrick().getColour()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
                    } else {
                        topRightPanel.add(new JLabel(new ImageIcon(getTile(0).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
                    }
                }
            }
            topRightPanel.revalidate();
            topRightPanel.repaint();
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                controller.moveLeft();
                controller.update();
                break;
            case KeyEvent.VK_RIGHT:
                controller.moveRight();
                controller.update();
                break;
            case KeyEvent.VK_UP:
                controller.rotate();
                controller.update();
                break;
            case KeyEvent.VK_DOWN:
                controller.setDown();
                controller.update();
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
