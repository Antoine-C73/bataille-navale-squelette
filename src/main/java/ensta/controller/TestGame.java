package ensta.controller;

import java.util.Arrays;
import java.util.List;

import ensta.ai.BattleShipsAI;
import ensta.ai.PlayerAI;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;

public class TestGame {

    private static List<AbstractShip> createDefaultShips() {
		return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier() });
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        
    public static void main(String[] args) {
        Board board = new Board("Board", 10);
        List<AbstractShip> ships = createDefaultShips();
        AbstractShip[] lShips = ships.toArray(new AbstractShip[0]);

        BattleShipsAI ai = new BattleShipsAI(board, board);
        ai.putShips(lShips);

        board.print();

        int sunken_ships = 0;

        Coords hitCoords = new Coords();

        while (sunken_ships != 5) {
            Hit hit = ai.sendHit(hitCoords);

            System.out.println("Tir en (" + hitCoords.getX() + ", " + hitCoords.getY() + ") :");

            if (hit.getValue() < 0) {
                if (hit == Hit.STRIKE) {
                    System.out.println("\n\nTouché !");
                } else {
                    System.out.println("\n\nTir raté...");
                }
            } else {
                System.out.println("\n\n" + hit + " coulé !");
                sunken_ships++;
            }

            board.print();
            System.out.println(sunken_ships);

            sleep(200);
        }
    }

}
