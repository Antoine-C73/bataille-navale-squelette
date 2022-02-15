package ensta.model;

import ensta.model.ship.AbstractShip;

public class Tile {
    private boolean isHit;
    private AbstractShip ship;
    private Coords coords;

    public Tile(Coords coords) {
        this.coords = coords;
        this.ship = null;
        this.isHit = false;
    }

    public AbstractShip getShip() {
        return this.ship;
    }

    public void setShip(AbstractShip ship) {
        this.ship = ship;
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
        return isHit;
    }

    public void setHit(boolean hit) {
        this.isHit = hit;
    } 
    
}
