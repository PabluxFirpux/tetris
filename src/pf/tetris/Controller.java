package pf.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller{
    private Board board;

    private Brick currentBrick;

    private Brick nextBrick;

    private Brick holdBrick;

    //TODO: Implement HOLD
    //Todo: Implement Start Menu

    private int score;

    private int delay;

    private int level;

    private int lines;

    private Game screen;

    boolean gameRunning;

    public Controller() {
        this.level = 1;
        this.lines = 0;
        this.gameRunning = true;
        this.board = new Board();
        this.currentBrick = new Brick();
        this.nextBrick = new Brick();
        this.score = 0;
        this.delay = 2000;
        screen = new Game(board, this);
        start();
    }

    public void start() {
        board.addBrick(currentBrick);
        javax.swing.Timer timer = new Timer(this.delay, e -> {
            pace();
            if(!gameRunning) {
                ((Timer) e.getSource()).stop();
                screen.getFrame().dispose();
            }
        });
        timer.start();
    }

    public void setDown() {
        board.setBrickDown(currentBrick);
        currentBrick = nextBrick;
        nextBrick = new Brick();
        board.addBrick(currentBrick);
        this.score += 10;
        update();
    }

    public void changeHold() {
        this.board.removeBrick(currentBrick);
        if(holdBrick == null) {
            holdBrick = currentBrick;
            currentBrick = nextBrick;
            nextBrick = new Brick();
        } else {
            Brick temp = holdBrick;
            holdBrick = currentBrick;
            temp.setXpos(currentBrick.getXpos());
            temp.setYpos(currentBrick.getYpos());
            currentBrick = temp;
        }
        this.board.addBrick(currentBrick);
        update();
    }

    public boolean hasHoldedBrick() {
        return holdBrick != null;
    }

    public Brick getHoldBrick() {
        return holdBrick;
    }

    public void update() {
        if(gameRunning) {
            checkForCompleteLines();
            checkForLoss();
            board.printBoard();
            System.out.println("Score: " + this.score);
            System.out.println("Next brick: ");
            currentBrick.printBrick();
            //game.refresh(board);
            if(lines > 10 && delay > 500) {
                level++;
                lines = 0;
                delay -= 100;
            }
        }
    }

    public void pace() {
        if (board.isBrickDone(currentBrick)) {
            currentBrick = nextBrick;
            nextBrick = new Brick();
            board.addBrick(currentBrick);
        } else {
            board.updateBrickDown(currentBrick);
        }
        update();
    }

    public void checkForLoss() {
        this.board.removeBrick(currentBrick);
        boolean loss = false;
        int[][] board = this.board.getBoard();
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] != 0) {
                loss = true;
            }
        }
        if (loss) {
            screen.dispose();
            gameRunning = false;
            EndScreen endScreen = new EndScreen(this.score);
        }
        this.board.addBrick(currentBrick);
    }

    public void checkForCompleteLines() {
        this.board.removeBrick(currentBrick);
        int[][] board = this.board.getBoard();
        int destroyedLines = 0;
        for (int i = 0; i < board.length; i++) {
            boolean completeLine = true;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    completeLine = false;
                }
            }
            if (completeLine) {
                destroyedLines++;
                this.lines++;
                this.board.destroyLine(i);
            }
        }
        addScoreLines(destroyedLines);
        this.board.addBrick(currentBrick);
    }

    public void addScoreLines(int x) {
        switch (x){
            case 1:
                this.score += 100;
                break;
            case 2:
                this.score += 300;
                break;
            case 3:
                this.score += 500;
                break;
            case 4:
                this.score += 800;
                break;
            default:
                break;
        }
    }

    public void moveLeft() {
        board.moveBrickLeft(currentBrick);
    }

    public void moveRight() {
        board.moveBrickRight(currentBrick);
    }

    public void rotate() {
        try {
            board.setBrickRotate(currentBrick);
        } catch (Exception e) {
            if(currentBrick.getXpos() < 5) {
                board.moveBrickRight(currentBrick);
                rotate();
            } else {
                board.moveBrickLeft(currentBrick);
                rotate();
            }
        }
    }

    public Brick getNextBrick() {
        return nextBrick;
    }

    public int getScore() {
        return score;
    }
}
