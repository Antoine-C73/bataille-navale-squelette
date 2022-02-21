package ensta.model;

import java.util.Random;

public class Coords {

    private int x;
    private int y;

    public Coords(Coords coords) {
        this(coords.getX(), coords.getY());
    }

    public Coords() {
        this.x = 0;
        this.y = 0;
    }

    public Coords(int x, int iy) {
        this.x = x;
        this.y = iy;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int i) {
        this.x = i;
    }

    public void setY(int i) {
        this.y = i;
    }

    public void setCoords(Coords res) {
        this.x = res.x;
        this.y = res.y;
    }

    public boolean isInBoard(int size) {
        return (this.x >= 0) && (this.x < size) && (this.y >= 0) && (this.y < size);
    }

    public static Coords randomCoords(int size) {
        Random randomEngine = new Random();
        int x = randomEngine.nextInt(size);
        int y = randomEngine.nextInt(size);
        return new Coords(x, y);
    }
    
}
