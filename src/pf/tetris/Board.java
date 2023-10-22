package pf.tetris;

public class Board {
    private int[][] board;

    public Board() {
        this.board = new int[20][10];
    }

    public void printBoard() {
        for (int[] row : this.board) {
            for (int cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void addBrick(Brick brick) {
        int[][] shape = brick.getShape();
        int colour = brick.getColour();
        int x = brick.getXpos();
        int y = brick.getYpos();
        for (int[] row : shape) {
            for (int cell : row) {
                if (cell == 1) {
                    this.board[y][x] = colour;
                }
                x++;
            }
            x = brick.getXpos();
            y++;
        }
    }

    public void destroyLine(int x) {
        for (int i = x; i > 0; i--) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = this.board[i-1][j];
            }
        }
    }

    public void removeBrick(Brick brick) {
        int[][] shape = brick.getShape();
        int x = brick.getXpos();
        int y = brick.getYpos();
        for (int[] row : shape) {
            for (int cell : row) {
                if (cell == 1) {
                    this.board[y][x] = 0;
                }
                x++;
            }
            x = brick.getXpos();
            y++;
        }
    }

    public void moveBrickDown(Brick brick) {
        removeBrick(brick);
        brick.moveDown();
        addBrick(brick);
    }

    public void moveBrickLeft(Brick brick) {
        removeBrick(brick);
        brick.moveLeft();
        addBrick(brick);
    }

    public void moveBrickRight(Brick brick) {
        removeBrick(brick);
        brick.moveRight();
        addBrick(brick);
    }

    public void rotateBrick(Brick brick) {
        removeBrick(brick);
        brick.rotate();
        addBrick(brick);
    }

    public boolean isBrickCollidingDown(Brick brick) {
        removeBrick(brick);
        int[][] shape = brick.getShape();
        int x = brick.getXpos();
        int y = brick.getYpos()+1;
        for (int[] row : shape) {
            for (int cell : row) {
                if (cell == 1) {
                    if (this.board[y][x] != 0) {
                        addBrick(brick);
                        return true;
                    }
                }
                x++;
            }
            x = brick.getXpos();
            y++;
        }
        addBrick(brick);
        return false;
    }

    public void setBrickDown(Brick brick) {
        while (!brick.isFullDown() && !isBrickCollidingDown(brick)) {
            moveBrickDown(brick);
        }
    }

    public void updateBrickDown(Brick brick) {
        if (!brick.isFullDown() && !isBrickCollidingDown(brick)) {
            moveBrickDown(brick);
        }
    }

    public boolean isBrickDone(Brick brick) {
        return !(!brick.isFullDown() && !isBrickCollidingDown(brick));
    }

    public boolean isBrickCollidingLeft(Brick brick) {
        removeBrick(brick);
        int[][] shape = brick.getShape();
        int x = brick.getXpos()-1;
        int y = brick.getYpos();
        for (int[] row : shape) {
            for (int cell : row) {
                if (cell == 1) {
                    if (this.board[y][x] != 0) {
                        addBrick(brick);
                        return true;
                    }
                }
                x++;
            }
            x = brick.getXpos()-1;
            y++;
        }
        addBrick(brick);
        return false;
    }

    public void setBrickLeft(Brick brick) {
        if (!brick.isFullLeft() && !isBrickCollidingLeft(brick)) {
            moveBrickLeft(brick);
        }
    }

    public boolean isBrickCollidingRight(Brick brick) {
        removeBrick(brick);
        int[][] shape = brick.getShape();
        int x = brick.getXpos()+1;
        int y = brick.getYpos();
        for (int[] row : shape) {
            for (int cell : row) {
                if (cell == 1) {
                    if (this.board[y][x] != 0) {
                        addBrick(brick);
                        return true;
                    }
                }
                x++;
            }
            x = brick.getXpos()+1;
            y++;
        }
        addBrick(brick);
        return false;
    }

    public void setBrickRight(Brick brick) {
        if (!brick.isFullRight() && !isBrickCollidingRight(brick)) {
            moveBrickRight(brick);
        }
    }

    public boolean isBrickCollidingRotate(Brick brick) {
        removeBrick(brick);
        int[][] shape = brick.getShape();
        int x = brick.getXpos();
        int y = brick.getYpos();
        int[][] newShape = new int[shape[0].length][shape.length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                newShape[j][shape.length - 1 - i] = shape[i][j];
            }
        }
        for (int[] row : newShape) {
            for (int cell : row) {
                if (cell == 1) {
                    if (this.board[y][x] != 0) {
                        addBrick(brick);
                        return true;
                    }
                }
                x++;
            }
            x = brick.getXpos();
            y++;
        }
        addBrick(brick);
        return false;
    }

    public void setBrickRotate(Brick brick) {
        if (!isBrickCollidingRotate(brick)) {
            rotateBrick(brick);
        }
    }
}
