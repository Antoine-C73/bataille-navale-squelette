package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.model.ship.ShipState;

public class Tile {
    private ShipState shipState;
    private AbstractShip ship;
    private Coords coords;

    public Tile(Coords coords) {
        this.coords = coords;
        this.ship = null;
        this.shipState = new ShipState(null);
    }

    public AbstractShip getShip() {
        return this.ship;
    }

    public void setShip(AbstractShip ship) {
        this.ship = ship;
        this.shipState.setShip(ship);
    }

    public boolean hasShip() {
        return (this.ship != null);
    }

    public Coords getCoords() {
        return this.coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public boolean isHit() {
        return (this.shipState.isStruck() != null);
    }

    public void setHit(Boolean hit) {
        this.shipState.addStrike(hit);
    } 
    
    public ShipState getShipState() {
        return this.shipState;
    }
}
