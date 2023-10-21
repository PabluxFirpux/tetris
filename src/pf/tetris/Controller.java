package pf.tetris;

public class Controller {
    private Board board;

    private Brick currentBrick;

    private Brick nextBrick;

    private int score;

    public Controller() {
        this.board = new Board();
        this.currentBrick = new Brick();
        this.nextBrick = new Brick();
        this.score = 0;
        start();
    }

    public void start() {
        board.addBrick(currentBrick);
        board.printBoard();
    }

    public void setDown() {
        board.setBrickDown(currentBrick);
        currentBrick = nextBrick;
        nextBrick = new Brick();
        board.addBrick(currentBrick);
        board.printBoard();
    }

    public void moveLeft() {
        board.moveBrickLeft(currentBrick);
        board.printBoard();
    }

    public void moveRight() {
        board.moveBrickRight(currentBrick);
        board.printBoard();
    }

    public void rotate() {
        board.rotateBrick(currentBrick);
        board.printBoard();
    }
}
