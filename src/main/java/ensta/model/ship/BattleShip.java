package ensta.model.ship;

import ensta.util.Orientation;

public class BattleShip extends AbstractShip {

    public BattleShip(Orientation orientation) {
        super("B", "BattleShip", 5, orientation);
    }

    public BattleShip() {
        super("B", "BattleShip", 5, Orientation.EAST);
    }
    
}
