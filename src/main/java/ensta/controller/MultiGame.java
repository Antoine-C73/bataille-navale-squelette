package ensta.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ensta.ai.PlayerAI;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;
import ensta.util.ColorUtil.Color;

/*

A rendre pour le 25/02/2022 avant 22h à mallard@oxyl.fr
(rendre jar exécutable, lien git et explications)

*/

public class MultiGame {

	/*
	 * *** Constante
	 */
	public static final File SAVE_FILE = new File("savegame.dat");

	/*
	 * *** Attributs
	 */
	private Player player1;
	private Player player2;
	private Scanner sin;

	/*
	 * *** Constructeurs
	 */
	public MultiGame() {
	}

	public MultiGame init() {
		if (!loadSave()) {


			Board playerBoard = new Board("Plateau joueur", 10);
			Board opponentBoard = new Board("Plateau opposant", 10);

			this.player1 = new Player(playerBoard, opponentBoard, createDefaultShips());
			this.player2 = new Player(opponentBoard, playerBoard, createDefaultShips());

			this.player1.putShips();
			this.player2.putShips();
		}
		return this;
	}

	/*
	 * *** Méthodes
	 */

	public void printGame(Player p1, Player p2) {
		String line = "Vous : ";
        int leftOffset = Integer.toString(p1.getBoard().getSize()).length() + 1;


        for (int i = line.length(); i < 2 * p1.getBoard().getSize() + leftOffset; i++) {
            line += " ";
        }

        line += "| Adversaire : ";
        System.out.println(line);

        line = "";
        for (int i = 0; i < leftOffset; i++) {
            line += " ";
        }

        String columns = "";

        for (int i = 0; i < p1.getBoard().getSize(); i++) {
            columns += p1.getBoard().base26(i) + " ";
        }
        
        line = line + columns + "| " + line + columns;

        System.out.println(line);		

        for (int i = 0; i < p1.getBoard().getSize(); i++) {
            line = "" + (i + 1);
            
            for (int s = Integer.toString(i+1).length(); s < leftOffset; s++) {
                line += " ";
            }

            for (int j = 0; j < p1.getBoard().getSize(); j++) {
				if (p1.getBoard().getHit(new Coords(j, i)) != null) {
					String x_string;
					if (p1.getBoard().getHit(new Coords(j, i))) {
						ColorUtil.Color color = Color.RED;
                        x_string = ColorUtil.colorize(p1.getBoard().getShip(new Coords(j, i)).getLabel(), color);
                    } else {
                        ColorUtil.Color color = Color.YELLOW;
                        x_string = ColorUtil.colorize(".", color);
                    }

                    line += x_string + " ";
                    
                } else {
					if (p1.getBoard().hasShip(new Coords(j, i))) {
						line += p1.getBoard().getShip(new Coords(j, i)).getLabel() + " ";
					} else {
						line += ". ";
					}
                }
            }

            line += "| ";

            line += (i + 1);
            
            for (int s = Integer.toString(i+1).length(); s < leftOffset; s++) {
                line += " ";
            }

            for (int j = 0; j < p1.getBoard().getSize(); j++) {
                if (p2.getBoard().getHit(new Coords(j, i)) != null) {
					String x_string;
					if (p2.getBoard().getHit(new Coords(j, i))) {
						ColorUtil.Color color = Color.RED;
                        x_string = ColorUtil.colorize(p2.getBoard().getShip(new Coords(j, i)).getLabel(), color);
                    } else {
                        ColorUtil.Color color = Color.YELLOW;
                        x_string = ColorUtil.colorize(".", color);
                    }

                    line += x_string + " ";
                    
                } else {
                    line += ". ";
                }                
			}

            System.out.println(line);            
        }

    }

	public void run() {
		Coords coords = new Coords();
		Board b1 = player1.getBoard();
		Hit hit;

		// main loop
		printGame(this.player1, this.player2);
		boolean done;
		do {
			System.out.println("C'est au joueur " + player1.getBoard().getName() + " de jouer !");
			printGame(this.player1, this.player2);

			hit = player1.sendHit(coords); // TODO player1 send a hit
			boolean strike = hit != Hit.MISS; // TODO set this hit on his board (b1)
			player2.getBoard().setHit(strike, coords);

			done = updateScore();
			System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

			printGame(this.player1, this.player2);

			// save();

			if (!done && !strike) {
				do {
                    System.out.println("C'est au joueur " + player2.getBoard().getName() + " de jouer !");
			        printGame(this.player2, this.player1);

					hit = player2.sendHit(coords); // TODO player2 send a hit.

					strike = hit != Hit.MISS;
					if (strike) {
						printGame(this.player2, this.player1);
					}
					System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
					done = updateScore();

					if (!done) {
//						save();
					}
				} while (strike && !done);
			}

		} while (!done);

		SAVE_FILE.delete();
		System.out.println(String.format("joueur %d gagne", player1.isLose() ? 2 : 1));
		// sin.close();
	}

	private void save() {
//		try {
//			// TODO bonus 2 : uncomment
//			// if (!SAVE_FILE.exists()) {
//			// SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
//			// }
//
//			// TODO bonus 2 : serialize players
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private boolean loadSave() {
//		if (SAVE_FILE.exists()) {
//			try {
//				// TODO bonus 2 : deserialize players
//
//				return true;
//			} catch (IOException | ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
		return false;
	}

	private boolean updateScore() {
		for (Player player : new Player[] { player1, player2 }) {
			int destroyed = 0;
			for (AbstractShip ship : player.getShips()) {
				if (ship.isSunk()) {
					destroyed++;
				}
			}

			player.setDestroyedCount(destroyed);
			player.setLose(destroyed == player.getShips().length);
			if (player.isLose()) {
				return true;
			}
		}
		return false;
	}

	private String makeHitMessage(boolean incoming, Coords coords, Hit hit) {
		String msg;
		ColorUtil.Color color = ColorUtil.Color.RESET;
		switch (hit) {
		case MISS:
			msg = hit.toString();
			break;
		case STRIKE:
			msg = hit.toString();
			color = ColorUtil.Color.RED;
			break;
		default:
			msg = hit.toString() + " coulé";
			color = ColorUtil.Color.RED;
		}
		msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords.getX())),
				(coords.getY() + 1), msg);
		return ColorUtil.colorize(msg, color);
	}

	private static List<AbstractShip> createDefaultShips() {
		return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new BattleShip(),
				new Carrier() });
	}
}
