package battleship;

/**
 * Represents a battleship, inherited from Ship class.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
class Battleship extends Ship {
	//static variables
	/**
	 * The length of the battleship.
	 */
	static final int LENGTH = 4;

	/**
	 * The type of the battleship.
	 */
	static final String TYPE = "battleship"; 

	//constructor
	/**
	 * Creates battleship with specific length.
	 */
	public Battleship() {
		//calls the constructor in the superclass Ship
		super(Battleship.LENGTH);
	}

	/**
	 * Returns the type of the battleship as a String.
	 */
	@Override
	public String getShipType() {
		return Battleship.TYPE;
	}

}
