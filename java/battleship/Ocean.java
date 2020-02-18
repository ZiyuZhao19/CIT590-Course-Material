package battleship;

import java.util.Random;

/**
 * Represents the 10X10 ocean.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
class Ocean {

	//instance variables
	/**
	 * Creates a 2-dimensional array representing the location of the ship.
	 */
	private Ship[][] ships = new Ship[10][10];

	/**
	 * The total number of shots fired by the user.
	 */
	private int shotsFired;

	/**
	 * The number of times a shot hit a ship.
	 */
	private int hitCount;

	/**
	 * The number of sunk ships.
	 */
	private int shipsSunk;

	//static variables
	/**
	 * The total number of battleship.
	 */
	private static final int NUM_OF_BATTLESHIP = 1;

	/**
	 * The total number of cruisers.
	 */
	private static final int NUM_OF_CRUISERS = 2;

	/**
	 * The total number of destroyers.
	 */
	private static final int NUM_OF_DESTROYERS = 3;

	/**
	 * The total number of submarines.
	 */
	private static final int NUM_OF_SUBMARINES = 4;

	//constructor
	/**
	 * Creates an empty ocean.
	 * Initializes the game variables.
	 */
	public Ocean() {
		//fills the Ocean with EmptySea objects
		Ocean.fillEmptySea(this.ships.length, this.ships[0].length, this.ships);

		//initializes the game variables
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
	}

	/**
	 * Helper function to fill the ships array with EmptySea objects.
	 * @param rows of the array
	 * @param columns of the array
	 * @param ships array
	 */
	private static void fillEmptySea(int rows, int columns, Ship[][] ships) {
		//iterates over the ships array to place new EmptySea objects
		//sets the bowRow and bowColumn for each EmptySea
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				ships[i][j] = new EmptySea();
				ships[i][j].setBowColumn(j);
				ships[i][j].setBowRow(i);
			}
		}
	}

	//methods
	/**
	 * Places all ten ships randomly on the empty ocean.
	 */
	void placeAllShipsRandomly() {
		//creates variables 
		Random rand = new Random();
		int row;
		int column;
		boolean horizontal;

		//places one battleship
		//each time creates an instance of Battleship
		//generates random number for row, column and horizontal variables
		//replaces the ship if the ships overlap or go out of bound of Ocean
		//otherwise, places the ship at that location
		for (int i = 0; i < Ocean.NUM_OF_BATTLESHIP; i++) {
			Ship battleship = new Battleship();
			row = rand.nextInt(10);
			column = rand.nextInt(10);
			horizontal = rand.nextBoolean();
			while (battleship.okToPlaceShipAt(row, column, horizontal, this) == false) {
				row = rand.nextInt(10);
				column = rand.nextInt(10);
				horizontal = rand.nextBoolean();
			}
			battleship.placeShipAt(row, column, horizontal, this);
		}	
		//places two cruisers
		//each time creates an instance of Cruiser
		//generates random number for row, column and horizontal variables
		//replaces the ship if the ships overlap or go out of bound of Ocean
		//otherwise, places the ship at that location
		Ship cruiser;
		for (int i = 0; i < Ocean.NUM_OF_CRUISERS; i++) {
			cruiser = new Cruiser();
			row = rand.nextInt(10);
			column = rand.nextInt(10);
			horizontal = rand.nextBoolean();
			while (cruiser.okToPlaceShipAt(row, column, horizontal, this) == false) {
				row = rand.nextInt(10);
				column = rand.nextInt(10);
				horizontal = rand.nextBoolean();
			}
			cruiser.placeShipAt(row, column, horizontal, this);
		}	

		//places three destroyers
		//each time creates an instance of Destroyer
		//generates random number for row, column and horizontal variables
		//replaces the ship if the ships overlap or go out of bound of Ocean
		//otherwise, places the ship at that location
		Ship destroyer;
		for (int i = 0; i < Ocean.NUM_OF_DESTROYERS; i++) {
			destroyer = new Destroyer();
			row = rand.nextInt(10);
			column = rand.nextInt(10);
			horizontal = rand.nextBoolean();
			while (destroyer.okToPlaceShipAt(row, column, horizontal, this) == false) {
				row = rand.nextInt(10);
				column = rand.nextInt(10);
				horizontal = rand.nextBoolean();
			}
			destroyer.placeShipAt(row, column, horizontal, this);
		}	

		//places four submarines
		//each time creates an instance of Submarine
		//generates random number for row, column and horizontal variables
		//replaces the ship if the ships overlap or go out of bound of Ocean
		//otherwise, places the ship at that location
		Ship submarine;
		for (int i = 0; i < Ocean.NUM_OF_SUBMARINES; i++) {
			submarine = new Submarine();
			row = rand.nextInt(10);
			column = rand.nextInt(10);
			horizontal = rand.nextBoolean();
			while (submarine.okToPlaceShipAt(row, column, horizontal, this) == false) {
				row = rand.nextInt(10);
				column = rand.nextInt(10);
				horizontal = rand.nextBoolean();
			}
			submarine.placeShipAt(row, column, horizontal, this);
		}	
	}

	/**
	 * Checks if the given location contains a ship.
	 * @param row of the location
	 * @param column of the location
	 * @return true if the location contains a ship
	 */
	boolean isOccupied(int row, int column) {
		//checks the ship type at the given location
		if (ships[row][column].getShipType().equals("empty")) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the given location contains a real ship.
	 * Updates the number of shots that have been fired and the number of hits.
	 * @param row of given location
	 * @param column of given location
	 * @return true if the given location contains a real ship, otherwise returns false.
	 * Once the ship has been sunk, additional shots at its location returns false.
	 */
	boolean shootAt(int row, int column) {
		//gets the ship object at the given location
		Ship ship = this.ships[row][column];

		//if the ship is shot sunk for the first time
		//increments shipsSunk, hitCount and shotsFired variables
		//returns true
		if ((ship.isSunk() == true)
				&& (ship.getAlreadySunk() == false)) {
			this.shipsSunk += 1;
			this.hitCount += 1;
			this.shotsFired += 1;
			return true;
		}

		//under other circumstances, if the given location contains a real ship
		//increments hitCount and shotsFired but not shipsSunk variables, returns true
		//otherwise, only increments the shotsFired and returns false
		if (this.isOccupied(row, column) == true) {	
			this.hitCount += 1;
			this.shotsFired += 1;
			return true;
		} else {
			this.shotsFired += 1;
			return false;
		}
	}

	/**
	 * @return the number of shots fired
	 */
	int getShotsFired() {
		return this.shotsFired;
	}

	/**
	 * @return the number of hits recorded.
	 */
	int getHitCount() {
		return this.hitCount;
	}

	/**
	 * @return the number of ships sunk.
	 */
	int getShipsSunk() {
		return this.shipsSunk;
	}

	/**
	 * Returns true if all the ships have been sunk, 
	 * otherwise, returns false.
	 * @return true if all the ships have been sunk
	 */
	boolean isGameOver() {
		//checks the shipsSunk variable
		if (this.getShipsSunk() == 10) {
			return true;
		}
		return false;
	}

	/**
	 * @return the 10X10 array of ships
	 */
	Ship[][] getShipArray() {
		return this.ships;
	}

	/**
	 * Prints the ocean.
	 */
	void print() {
		//creates an int to check if part of the ship is hit
		int num;

		//iterates to print the ocean
		//calls findHitIndex function to check if the given part of the ship is hit
		//prints "x"(hit but not sunk), "s"(sunk) or "-"(empty) if that part is hit
		//otherwise, prints "."
		System.out.println("  0 1 2 3 4 5 6 7 8 9 ");
		for (int i = 0; i <= 9; i++) {
			System.out.print(i + " ");
			for (int j = 0; j <= 9; j++) {
				num = this.findHitIndex(i, j);
				if (this.getShipArray()[i][j].getHit()[num] == true) {
					System.out.print(this.getShipArray()[i][j] + " ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.println();
		}
		System.out.println("Enter row,column: ");
	}

	/**
	 * Finds which part of the ship is hit.
	 * @param row given row
	 * @param column given column
	 * @return an int index
	 */
	private int findHitIndex(int row, int column) {
		//if the ship is horizontal, subtracts the given column from the bowColumn 
		//if the ship is vertical, subtracts the given row from the bowRow
		int index;
		if (this.getShipArray()[row][column].isHorizontal() == true) {
			index = this.getShipArray()[row][column].getBowColumn() - column;
		} else {
			index = this.getShipArray()[row][column].getBowRow() - row;	
		}
		return index;
	}

	/**
	 * Prints the answer of the game for testing.
	 */
	void printAns() {
		System.out.println("\n  0 1 2 3 4 5 6 7 8 9 "); 
		for (int i = 0; i <= 9; i++) {
			System.out.print(i + " ");
			for (int j = 0; j <= 9; j++) {
				System.out.print(this.getShipArray()[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Enter row,column: ");
	}

}
