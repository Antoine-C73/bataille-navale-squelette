package ensta.model.ship;

import ensta.util.Orientation;

public class Destroyer extends AbstractShip {
    public Destroyer(Orientation orientation) {
        super("label", "Destroyer", 5, orientation);
    }

    public Destroyer() {
        super("label", "Destroyer", 5, Orientation.EAST);
    }
}
