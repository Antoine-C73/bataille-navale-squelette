package ensta.model.ship;

import ensta.util.Orientation;

public abstract class AbstractShip {

    protected String label;
    protected String name;
    protected int length;
    protected Orientation orientation;

    public AbstractShip(String label, String name, int length, Orientation orientation) {
        this.label = label;
        this.name = name;
        this.length = length;
        this.orientation = orientation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int value) {
        this.length = value;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Object getName() {
        return name;
    }

    public boolean isSunk() {
        return false;
    }
    
}
