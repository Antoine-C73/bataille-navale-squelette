package ensta.model;

import java.util.Arrays;
import java.util.Collections;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;

public class Board implements IBoard {

    private static final int DEFAULT_SIZE = 10;

    private String name;
    private int size;
    private Tile tiles[][];
    
    public Board(String name, int size) {
        this.name = name;
        this.size = size;
        this.tiles = new Tile[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = new Tile(new Coords(j, i));
            }
        }
    }

    public Board(String name) {
        this(name, Board.DEFAULT_SIZE);
    }

    private String base26(int n) {
        String ret = "";

        if (n < 26) {
            ret += (char) (n + 'A');
            return ret;
        }

        while (n >= 26) {
            ret += (char) ((n % 26) + 'A');
            n /= 26;

        }
        
        ret += (char) ((n - 1) + 'A');
        char[] retArray = ret.toCharArray();

        char[] revRet = new char[ret.length()];
        for (int i = 0; i < ret.length(); i++) {
            revRet[ret.length() - i - 1] = retArray[i];
        }

        return new String(revRet);
    }

    public void print() {
        String line = "Navires : ";
        int leftOffset = Integer.toString(this.size).length() + 1;


        for (int i = line.length(); i < 2 * this.size + leftOffset; i++) {
            line += " ";
        }

        line += "| Tirs : ";
        System.out.println(line);

        line = "";
        for (int i = 0; i < leftOffset; i++) {
            line += " ";
        }

        String columns = "";

        for (int i = 0; i < this.size; i++) {
            columns += base26(i) + " ";
        }
        
        line = line + columns + "| " + line + columns;

        System.out.println(line);		

        for (int i = 0; i < this.size; i++) {
            line = "" + (i + 1);
            
            for (int s = Integer.toString(i+1).length(); s < leftOffset; s++) {
                line += " ";
            }

            for (int j = 0; j < this.size; j++) {
                line += this.tiles[i][j].hasShip() ? "X " : ". ";
            }

            line += "| ";

            line += (i + 1);
            
            for (int s = Integer.toString(i+1).length(); s < leftOffset; s++) {
                line += " ";
            }

            for (int j = 0; j < this.size; j++) {
				line += this.tiles[i][j].isHit() ? "X " : ". ";
			}

            System.out.println(line);            
        }

    }

    public boolean canPutShip(AbstractShip ship, Coords coords) {
        Orientation o = ship.getOrientation();
        int dx = 0, dy = 0;
        if (o == Orientation.EAST) {
            if (coords.getX() + ship.getLength() >= this.size) {
                return false;
            }
            dx = 1;
        } else if (o == Orientation.SOUTH) {
            if (coords.getY() + ship.getLength() >= this.size) {
                return false;
            }
            dy = 1;
        } else if (o == Orientation.NORTH) {
            if (coords.getY() + 1 - ship.getLength() < 0) {
                return false;
            }
            dy = -1;
        } else if (o == Orientation.WEST) {
            if (coords.getX() + 1 - ship.getLength() < 0) {
                return false;
            }
            dx = -1;
        }

        Coords iCoords = new Coords(coords);

        for (int i = 0; i < ship.getLength(); ++i) {
            if (this.hasShip(iCoords)) {
                return false;
            }
            iCoords.setX(iCoords.getX() + dx);
            iCoords.setY(iCoords.getY() + dy);
        }

        return true;
    }

    public int getSize() {
        return size;
    }

    public boolean putShip(AbstractShip ship, Coords coords) {
        if (canPutShip(ship, coords)) {
            int x = coords.getX();
            int y = coords.getY();

            for (int i = 0; i < ship.getLength(); i++) {
                switch (ship.getOrientation()) {
                    case NORTH:
                        this.tiles[y - i][x].setShip(ship);                        
                        break;
                    case SOUTH:
                        this.tiles[y + i][x].setShip(ship);
                        break;
                    case EAST:
                        this.tiles[y][x + i].setShip(ship);
                        break;
                    case WEST:
                        this.tiles[y][x - i].setShip(ship);
                        break;
                }
            }

            return true;

        }

        return false;
    }

    @Override
    public boolean hasShip(Coords coords) {
        int x = coords.getX();
        int y = coords.getY();
        return this.tiles[y][x].hasShip();
    }

    @Override
    public void setHit(boolean hit, Coords coords) {
        int x = coords.getX();
        int y = coords.getY();
        this.tiles[y][x].setHit(hit);
        
    }

    @Override
    public Boolean getHit(Coords coords) {
        int x = coords.getX();
        int y = coords.getY();
        return this.tiles[y][x].isHit();
    }

    @Override
    public Hit sendHit(Coords res) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
