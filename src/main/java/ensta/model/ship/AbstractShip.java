package ensta.model.ship;

import ensta.util.Orientation;

public abstract class AbstractShip {

    public int getLength() {
        return 0;
    }

    public Orientation getOrientation() {
        return null;
    }

    public Object getName() {
        return null;
    }

    public boolean isSunk() {
        return false;
    }
    
}
