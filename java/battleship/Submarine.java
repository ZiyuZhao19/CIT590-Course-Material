package battleship;

/**
 * Represents a submarine, inherited from Ship class.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
public class Submarine extends Ship {
	//static variables
	/**
	 * The length of the submarine.
	 */
	static final int LENGTH = 1;

	/**
	 * The type of the submarine.
	 */
	static final String TYPE = "submarine";

	/**
	 * Creates submarine with specific length.
	 */
	public Submarine() {
		super(Submarine.LENGTH);
	}

	/**
	 * Returns the type of the submarine as a String.
	 */
	@Override
	public String getShipType() {
		return Submarine.TYPE;
	}

}
