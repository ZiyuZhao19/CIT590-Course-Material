package battleship;

/**
 * Represents emptySea, inherited from Ship class.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
public class EmptySea extends Ship {
	//static variables
	/**
	 * Represents the length of EmptySea "ship".
	 */
	static final int LEGNTH = 1;

	/**
	 * The type of the EmptySea.
	 */
	static final String TYPE = "empty";

	/**
	 * Array representing if the area is hit.
	 */
	boolean[] hit = new boolean[1];

	//constructor
	/**
	 * Creates an EmptySea type "ship".
	 */
	public EmptySea() {
		//calls constructor in superclass Ship
		super(LEGNTH);
	}

	/**
	 * @return the hit array
	 * Overrides the getHit method inherited from ship.
	 */
	@Override
	public boolean[] getHit() {
		return this.hit;
	}

	/**
	 * Returns false to indicate nothing was hit.
	 * Overrides the shootAt method inherited from Ship.
	 */
	@Override
	boolean shootAt(int row, int column) {
		this.hit[0] = true;
		return false;
	}

	/**
	 * Returns false to indicate nothing was sunk.
	 * Overrides the isSunk method inherited from Ship.
	 */
	@Override
	boolean isSunk() { 
		return false;
	}

	/**
	 * Returns the single-character "-" String.
	 */
	@Override
	public String toString() {
		return "-";
	}

	/**
	 * Gets the name for the ship type.
	 */
	@Override
	public String getShipType() {
		return EmptySea.TYPE;
	}

}
