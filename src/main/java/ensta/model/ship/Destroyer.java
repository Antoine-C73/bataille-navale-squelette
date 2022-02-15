package ensta.model.ship;

import ensta.util.Orientation;

public class Destroyer extends AbstractShip {
    public Destroyer(Orientation orientation) {
        super("D", "Destroyer", 5, orientation);
    }

    public Destroyer() {
        super("D", "Destroyer", 5, Orientation.EAST);
    }
}
