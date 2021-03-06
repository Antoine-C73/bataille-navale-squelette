package ensta.model.ship;

import ensta.util.ColorUtil;
import ensta.util.ColorUtil.Color;

public class ShipState {

    private AbstractShip ship;
    private Boolean struck;

    public ShipState(AbstractShip ship) {
        this.ship = ship;
        this.struck = null;
    }

    public void addStrike(Boolean hit) {
        this.struck = hit;

        if (this.ship != null) {
            this.ship.addStrike();
        }
    }

    public Boolean isStruck() {
        return this.struck;
    }

    public String toString() {
        ColorUtil.Color color = this.struck ? Color.RED: Color.WHITE;
        return ColorUtil.colorize(this.ship.label, color);
    }

    public boolean isSunk() {
        if (this.ship != null) {
            return this.ship.isSunk();
        }
        return false;
    }

    public AbstractShip getShip() {
        return this.ship;
    }

    public void setShip(AbstractShip ship) {
        this.ship = ship;
    }



    
}
