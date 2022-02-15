package ensta.model.ship;

import ensta.util.Orientation;
public class Carrier extends AbstractShip {
    public Carrier(Orientation orientation) {
        super("label", "Carrier", 5, orientation);
    }

    public Carrier() {
        super("label", "Carrier", 5, Orientation.EAST);
    }
}
