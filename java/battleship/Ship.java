package battleship;

/**
 * Describes the characteristics common to all ships.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
abstract class Ship {
	//instance variables
	/**
	 * The row that contains the bow.
	 */
	private int bowRow;

	/**
	 * The column that contains the bow.
	 */
	private int bowColumn;

	/**
	 * The length of the ship.
	 */
	private int length;

	/**
	 * A boolean that represents whether the ship is going to be placed horizontally or vertically.
	 */
	private boolean horizontal;

	/**
	 * An array of 4  booleans that indicates whether that part of the ship has been hit or not.
	 */
	private boolean[] hit;

	/**
	 * A boolean that represents whether the area has been fired.
	 */
	private boolean alreadySunk;

	//constructor
	/**
	 * Sets the length property of the particular ship.
	 * Initializes the array hit.
	 * @param length of the given ship
	 */
	public Ship(int length) {
		this.length = length;
		this.hit = new boolean[4];
	}

	//getter and setter methods
	/**
	 * @return the ship length
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * @return the bowRow corresponding to the position of the bow
	 */
	public int getBowRow() {
		return this.bowRow;
	}

	/**
	 * @return the bow column location
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}

	/**
	 * @return the hit array 
	 */
	public boolean[] getHit() {
		return this.hit;
	}

	/**
	 * @return whether the ship is horizontal or not
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}

	/**
	 * @param bowRow the bowRow to set
	 */
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}

	/**
	 * @param bowColumn the bowColumn to set
	 */
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}

	/**
	 * @param horizontal the value of instance variable horizontal to set
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * @return the fired boolean
	 */
	public boolean getAlreadySunk() {
		return this.alreadySunk;
	}

	/**
	 * @param shot the value of instance variable shot to set
	 */
	public void setAlreadySunk(boolean fired) {
		this.alreadySunk = fired;
	}

	//abstract methods
	/**
	 * Returns the type of the ship as a String.
	 * @return the type of the ship
	 */
	public abstract String getShipType();

	//other methods
	/**
	 * Based on the given row, column, and orientation, checks if it is okay to place the ship at the given location.
	 * @param row of the bow
	 * @param column of the bow
	 * @param horizontal indicates places the ship vertically or horizontally
	 * @param ocean array of ships
	 * @return
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		//check for horizontal ships
		if (horizontal == true) {
			//check if the ship sticks out
			if (column + 1 - this.getLength() < 0) {
				return false;
			}
			//iterate over the spots on and around the ship
			//check if it's "empty"
			//return yes if the spots are all "empty"
			//otherwise, return false
			for (int i = row - 1; i < row + 2; i++) {
				for (int j = column - this.getLength(); j < column + 2; j++) {
					try {
						if (ocean.getShipArray()[i][j].getShipType().equals("empty")) {
							continue;
						} else {
							return false;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						continue;
					} 
				}
			}
		}

		//check for vertical ships
		if (horizontal == false) {
			//check if the ship sticks out
			if (row + 1 - this.getLength() < 0) {
				return false;
			}
			//iterate over the spots on and around the ship
			//check if it's "empty"
			//return yes if the spots are all "empty"
			//otherwise, return false
			for (int i = row - this.getLength(); i < row + 2;i++) {
				for (int j = column - 1; j < column + 2;j++) {
					try {
						if (ocean.getShipArray()[i][j].getShipType().equals("empty")) {
							continue;
						} else {
							return false;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						continue;
					} 
				}
			}			
		}
		return true;
	}

	/**
	 * Puts the ship in the ocean.
	 * Assumes all the horizontal ships face East,
	 * all the vertical ships face South.
	 * @param row of the bow
	 * @param column of the bow
	 * @param horizontal to place the ship horizontally or vertically
	 * @param ocean object containing the ships array
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		//gives values to the bowRow, bowColumn and horizontal instance variables
		this.setBowColumn(column);
		this.setBowRow(row);
		this.setHorizontal(horizontal);

		//puts the horizontal ship in the ocean
		if (horizontal == true) {
			for (int i = 0; i < this.getLength(); i++) {
				ocean.getShipArray()[row][column - i] = this;
			}
		}

		//puts the vertical ship in the ocean
		if (horizontal == false) {
			for (int j = 0; j < this.getLength(); j++) {
				ocean.getShipArray()[row - j][column] = this;
			}
		}	
	}

	/**
	 * If the part of the ship occupies the given row and column
	 * and the ship hasn't been sunk,
	 * mark that part of the ship and returns true,
	 * otherwise returns false.
	 * @param row of the shoot
	 * @param column of shoot
	 * @return true if hits the ship, otherwise returns false
	 */
	boolean shootAt(int row, int column) {
		//check if the ship has been sunk already
		if (this.isSunk() == false) {

			//if not sunk, iterate over the spots covered by the ship
			//if the given row and column refers to a covered spot
			//mark it as getHit
			for (int i = 0; i < this.getLength();i++) {

				//check for horizontal ships
				if (((this.getBowRow() == row) && (this.getBowColumn() == column + i))

						//check for vertical ships
						||((this.getBowRow() == row + i) && (this.getBowColumn() == column))) {
					this.getHit()[i] = true;
					return true;
				}
			}
		}

		//if the ship is already sunk, mark alreadySunk as true
		this.setAlreadySunk(true);
		return false;
	}

	/**
	 * Returns true if every part of the ship has been hit,
	 * otherwise returns false
	 * @return true if the hit is sunk, otherwise returns false
	 */
	boolean isSunk() {

		//iterate over the spots on the ship
		for (int i = 0; i < this.getLength(); i++) {

			//if not all spots has been hit, return false
			//otherwise, return true
			if (this.getHit()[i] == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns a single-character String.
	 * Returns "s" if the ship has been sunk.
	 * Returns "x" if it has not been sunk.
	 */
	@Override
	public String toString() {

		//for a sunk ship, display its spots by "s"
		if (this.isSunk() == true) {
			return "s";
		}

		//for spots hit on a not-sunk ship, display the hit by "x"
		return "x";
	}

}
