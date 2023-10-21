package pf.tetris;

public class Brick {
    private int colour;

    private int xpos;

    private int ypos;

    private int[][] shape;

    public Brick() {
        this.colour = findColour();
        this.shape = findShape();
        this.xpos = 5;
        this.ypos = 0;
    }

    private int findColour() {
        return getRandomNumber(1,9);
    }

    private int[][] findShape() {
        int x = getRandomNumber(1,7);
        switch (x){
            case 1:
                return new int[][] {{1,1,1,1}};
            case 2:
                return new int[][] {{1,1},{1,1}};
            case 3:
                return new int[][] {{1,1,1},{0,1,0}};
            case 4:
                return new int[][] {{1,1,1},{1,0,0}};
            case 5:
                return new int[][] {{1,1,1},{0,0,1}};
            case 6:
                return new int[][] {{1,1,0},{0,1,1}};
            case 7:
                return new int[][] {{0,1,1},{1,1,0}};
            default:
                return new int[][] {{1,1,1,1}};
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public int[][] getShape() {
        return shape;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void moveLeft() {
        if(this.xpos > 0) {
            this.xpos--;
        }
    }

    public void moveRight() {
        if(this.xpos < 10 - this.shape[0].length) {
            this.xpos++;
        }
    }

    public void moveDown() {
        if(this.ypos < (20 - this.shape.length)) {
            this.ypos++;
        }
    }

    public void rotate() {
        int[][] newShape = new int[this.shape[0].length][this.shape.length];
        for (int i = 0; i < this.shape.length; i++) {
            for (int j = 0; j < this.shape[0].length; j++) {
                newShape[j][this.shape.length - 1 - i] = this.shape[i][j];
            }
        }
        this.shape = newShape;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public boolean isFullDown() {
        return this.ypos == 20 - this.shape.length;
    }

    public boolean isFullRight() {
        return this.xpos == 10 - this.shape[0].length;
    }

    public boolean isFullLeft() {
        return this.xpos == 0;
    }
}
