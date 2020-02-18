package battleship;

/**
 * Represents a cruiser, inherited from Ship class.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
class Cruiser extends Ship {
	//static variables
	/**
	 * The length of the cruiser.
	 */
	static final int LENGTH = 3;

	/**
	 * The type of the cruiser.
	 */
	static final String TYPE = "cruiser";

	//constructor
	/**
	 * Creates cruiser with specific length.
	 */
	public Cruiser() {
		//calls the constructor in superclass Ship
		super(Cruiser.LENGTH);
	}

	//methods
	/**
	 * Returns the type of the cruiser as a String.
	 */
	@Override
	public String getShipType() {
		return Cruiser.TYPE;
	}

}
