package battleship;

/**
 * Represents a destroyer, inherited from Ship class.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
public class Destroyer extends Ship {
	//static variables
	/**
	 * The length of the destroyer.
	 */
	static final int LENGTH = 2;

	/**
	 * The type of the destroyer.
	 */
	static final String TYPE = "destroyer";

	//constructor
	/**
	 * Creates destroyer with specific length.
	 */
	public Destroyer() {
		//calls constructor in superclass Ship
		super(Destroyer.LENGTH);
	}

	/**
	 * Returns the type of the destroyer as a String.
	 */
	@Override
	public String getShipType() {
		return Destroyer.TYPE;
	}

}
