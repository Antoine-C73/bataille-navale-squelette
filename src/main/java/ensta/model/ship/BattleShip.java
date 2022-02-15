package ensta.model.ship;

import ensta.util.Orientation;

public class BattleShip extends AbstractShip {

    public BattleShip(Orientation orientation) {
        super("label", "BattleShip", 5, orientation);
    }

    public BattleShip() {
        super("label", "BattleShip", 5, Orientation.EAST);
    }
    
}
