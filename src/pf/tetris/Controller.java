package pf.tetris;

public class Controller {
    private Board board;

    private Brick currentBrick;

    private Brick nextBrick;

    //TODO: Implement HOLD
    //Todo: Implement GUI

    private int score;

    private Game game;

    public Controller() {
        this.board = new Board();
        this.currentBrick = new Brick();
        this.nextBrick = new Brick();
        this.score = 0;
        start();
        game = new Game(board);
    }

    public void start() {
        board.addBrick(currentBrick);
    }

    public void setDown() {
        board.setBrickDown(currentBrick);
        currentBrick = nextBrick;
        nextBrick = new Brick();
        board.addBrick(currentBrick);
        this.score += 10;
        update();
    }

    public void update() {
        checkForCompleteLines();
        checkForLoss();
        if (board.isBrickDone(currentBrick)) {
            currentBrick = nextBrick;
            nextBrick = new Brick();
            board.addBrick(currentBrick);
        } else {
            board.updateBrickDown(currentBrick);
        }
        board.printBoard();
        System.out.println("Score: " + this.score);
        System.out.println("Next brick: ");
        currentBrick.printBrick();
        //game.refresh(board);
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
            System.out.println("You lose");
            System.exit(0);
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
}
