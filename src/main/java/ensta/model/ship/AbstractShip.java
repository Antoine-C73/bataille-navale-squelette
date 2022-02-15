package ensta.model.ship;

import ensta.util.Orientation;

public abstract class AbstractShip {

    protected String label;
    protected String name;
    protected int length;
    protected Orientation orientation;
    protected int strikeCount;

    public AbstractShip(String label, String name, int length, Orientation orientation) {
        this.label = label;
        this.name = name;
        this.length = length;
        this.orientation = orientation;
        this.strikeCount = 0;
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

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Object getName() {
        return name;
    }

    public String getLabel() {
        return this.label;
    }

    public void addStrike() {
        this.strikeCount = Math.min(this.strikeCount, this.length);
    }

    public boolean isSunk() {
        return (this.strikeCount == this.length);
    }
    
}
