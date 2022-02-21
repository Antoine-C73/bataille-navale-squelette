package ensta.model;

import java.io.Serializable;
import java.nio.file.OpenOption;
import java.util.List;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.view.InputHelper;

public class Player {
	/*
	 * ** Attributs
	 */
	private Board board;
	protected Board opponentBoard;
	private int destroyedCount;
	protected AbstractShip[] ships;
	private boolean lose;

	/*
	 * ** Constructeur
	 */
	public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
		this.setBoard(board);
		this.ships = ships.toArray(new AbstractShip[0]);
		this.opponentBoard = opponentBoard;
	}

	/*
	 * ** Méthodes
	 */

	/**
	 * Read keyboard input to get ships coordinates. Place ships on given
	 * coodrinates.
	 */
	public void putShips() {
		this.board.print();

		boolean done = false;
		int i = 0;

		do {
			AbstractShip ship = ships[i];

			boolean correct_choice = false;
			InputHelper.ShipInput res = new InputHelper.ShipInput();

			do {
				String msg = String.format("placer %d : %s(%d)", i + 1, ship.getName(), ship.getLength());
				System.out.println(msg);

				try {
					res = InputHelper.readShipInput();

					switch (res.orientation) {
						case "north":
							ship.setOrientation(Orientation.NORTH);					
							break;
						case "south":
							ship.setOrientation(Orientation.SOUTH);					
							break;
						case "east":
							ship.setOrientation(Orientation.EAST);					
							break;
						case "west":
							ship.setOrientation(Orientation.WEST);					
							break;
					}					

					correct_choice = this.board.canPutShip(ship, new Coords(res.x, res.y - 1));
					
					if (!correct_choice) {
						System.out.println("Positionnement invalide !");
					} else {
						this.board.putShip(ship, new Coords(res.x, res.y - 1));
					}					

				} catch (Exception e) {
					correct_choice = false;
				}
				
			} while (!correct_choice);
				
			++i;
			done = i == 5;

			board.print();
		} while (!done);
	}

	public Hit sendHit(Coords coords) {
		boolean done = false;
		Hit hit = null;

		InputHelper.CoordInput res;

		do {
			System.out.println("où frapper?");

			try {
					res = InputHelper.readCoordInput();
					hit = this.opponentBoard.sendHit(res.x, res.y);
					hit.setCoords(new Coords(res.x, res.y));
					done = true;
					coords.setCoords(new Coords(res.x, res.y));

			} catch (Exception e) {
				done = false;
			}
			
		} while (!done);

		return hit;
	}

	public AbstractShip[] getShips() {
		return ships;
	}

	public void setShips(AbstractShip[] ships) {
		this.ships = ships;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getDestroyedCount() {
		return destroyedCount;
	}

	public void setDestroyedCount(int destroyedCount) {
		this.destroyedCount = destroyedCount;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}
}
