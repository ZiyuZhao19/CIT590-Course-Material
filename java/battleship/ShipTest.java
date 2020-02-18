package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Ship class and its subclass
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
class ShipTest {

	/**
	 * Creates new Ship objects.
	 */
	Ship battleship;

	Ship cruiser;

	Ship destroyer;

	Ship submarine;

	Ship emptySea;

	Ocean ocean;

	@BeforeEach
	void setUp() throws Exception {

		this.battleship = new Battleship();

		this.cruiser = new Cruiser();

		this.destroyer = new Destroyer();

		this.submarine = new Submarine();

		this.emptySea = new EmptySea();

		this.ocean = new Ocean();

	}

	@Test
	void testShip() {	

		//initialize some boolean array
		boolean[] array = {false, false, false, false};
		boolean[] array2 = {false};

		//test the length of ships and their status when not fired upon
		assertEquals(4, this.battleship.getLength());
		assertTrue(Arrays.equals(array, this.battleship.getHit()));

		assertEquals(3, this.cruiser.getLength());
		assertTrue(Arrays.equals(array, this.cruiser.getHit()));

		assertEquals(2, this.destroyer.getLength());
		assertTrue(Arrays.equals(array, this.destroyer.getHit()));

		assertEquals(1, this.submarine.getLength());
		assertTrue(Arrays.equals(array, this.submarine.getHit()));

		assertEquals(1, this.emptySea.getLength());
		assertTrue(Arrays.equals(array2, this.emptySea.getHit()));

	}

	@Test
	void testGetLength() {

		assertEquals(4, battleship.getLength());

		assertEquals(3, cruiser.getLength());

		assertEquals(2, destroyer.getLength());

		assertEquals(1, submarine.getLength());

		assertEquals(1, emptySea.getLength());

	}

	@Test
	void testGetBowRow() {

		//test getBowRow after ship placement
		battleship.placeShipAt(0, 3, true, ocean);
		assertEquals(0, battleship.getBowRow());

		cruiser.placeShipAt(9, 9, true, ocean);
		assertEquals(9, cruiser.getBowRow());

		destroyer.placeShipAt(6, 5, false, ocean);
		assertEquals(6, destroyer.getBowRow());

		submarine.placeShipAt(5, 5, false, ocean);
		assertEquals(5, submarine.getBowRow());

		emptySea.placeShipAt(1, 2, false, ocean);
		assertEquals(1, emptySea.getBowRow());

	}

	@Test
	void testGetBowColumn() {

		//test getBowColumn after ship placement
		battleship.placeShipAt(0, 3, true, ocean);
		assertEquals(3, battleship.getBowColumn());

		cruiser.placeShipAt(9, 7, true, ocean);
		assertEquals(7, cruiser.getBowColumn());

		destroyer.placeShipAt(6, 7, true, ocean);
		assertEquals(7, destroyer.getBowColumn());

		submarine.placeShipAt(5, 8, false, ocean);
		assertEquals(8, submarine.getBowColumn());

		emptySea.placeShipAt(1, 0, true, ocean);
		assertEquals(0, emptySea.getBowColumn());
	}

	@Test
	void testGetHit() {

		//fills with ships
		//shoot the ship
		battleship.placeShipAt(0, 5, true, ocean);
		cruiser.placeShipAt(3, 3, true, ocean);
		destroyer.placeShipAt(4, 8, false, ocean);
		submarine.placeShipAt(6, 6, true, ocean);
		emptySea.placeShipAt(8, 9, true, ocean);
		battleship.shootAt(0, 2);
		cruiser.shootAt(3, 2);
		destroyer.shootAt(3, 8);
		submarine.shootAt(6, 6);
		emptySea.shootAt(8, 9);

		boolean[] array = {false, false, false, true};
		assertTrue(Arrays.equals(array, this.battleship.getHit()));

		array = new boolean[] {false, true, false, false};
		assertTrue(Arrays.equals(array, this.cruiser.getHit()));

		array = new boolean[] {false, true, false, false};
		assertTrue(Arrays.equals(array, this.destroyer.getHit()));

		array = new boolean[] {true, false, false, false};
		assertTrue(Arrays.equals(array, this.submarine.getHit()));

		array = new boolean[] {true};
		assertTrue(Arrays.equals(array, this.emptySea.getHit()));


	}

	@Test
	void testIsHorizontal() {

		//test horizontal after placing the ships
		battleship.placeShipAt(0, 3, true, ocean);
		assertEquals(true, battleship.isHorizontal());

		cruiser.placeShipAt(9, 9, false, ocean);
		assertEquals(false, cruiser.isHorizontal());

		destroyer.placeShipAt(7, 6, false, ocean);
		assertEquals(false, destroyer.isHorizontal());

		submarine.placeShipAt(5, 5, true, ocean);
		assertEquals(true, submarine.isHorizontal());

		emptySea.placeShipAt(1, 4, false, ocean);
		assertEquals(false, emptySea.isHorizontal());
	}

	@Test
	void testSetBowRow() {

		//use getBowRow function to test setBowRow
		battleship.setBowRow(0);
		assertEquals(0, battleship.getBowRow());

		cruiser.setBowRow(9);
		assertEquals(9, cruiser.getBowRow());

		destroyer.setBowRow(2);
		assertEquals(2, destroyer.getBowRow());

		submarine.setBowRow(5);
		assertEquals(5, submarine.getBowRow());

		emptySea.setBowRow(6);
		assertEquals(6, emptySea.getBowRow());
	}

	@Test
	void testSetBowColumn() {

		//use getBowColumn function to test setBowColumn
		battleship.setBowColumn(3);
		assertEquals(3, battleship.getBowColumn());

		cruiser.setBowColumn(7);
		assertEquals(7, cruiser.getBowColumn());

		destroyer.setBowColumn(4);
		assertEquals(4, destroyer.getBowColumn());

		submarine.setBowColumn(8);
		assertEquals(8, submarine.getBowColumn());

		emptySea.setBowColumn(1);
		assertEquals(1, emptySea.getBowColumn());
	}

	@Test
	void testSetHorizontal() {

		//use isHorizontal to test setHorizontal
		battleship.setHorizontal(true);
		assertEquals(true, battleship.isHorizontal());

		cruiser.setHorizontal(true);
		assertEquals(true, cruiser.isHorizontal());

		destroyer.setHorizontal(true);
		assertEquals(true, destroyer.isHorizontal());

		submarine.setHorizontal(false);
		assertEquals(false, submarine.isHorizontal());

		emptySea.setHorizontal(false);
		assertEquals(false, emptySea.isHorizontal());

	}

	@Test
	void testGetAlreadySunk() {

		//places ships
		battleship.placeShipAt(0, 3, true, ocean);
		cruiser.placeShipAt(6, 1, false, ocean);
		destroyer.placeShipAt(4, 8, true, ocean);
		submarine.placeShipAt(9, 9, false, ocean);

		assertEquals(false, this.ocean.getShipArray()[0][3].getAlreadySunk());
		battleship.shootAt(0, 3);
		assertEquals(false, this.ocean.getShipArray()[0][3].getAlreadySunk());

		assertEquals(false, this.ocean.getShipArray()[6][1].getAlreadySunk());
		cruiser.shootAt(6, 1);
		assertEquals(false, this.ocean.getShipArray()[6][1].getAlreadySunk());

		assertEquals(false, this.ocean.getShipArray()[4][8].getAlreadySunk());
		destroyer.shootAt(4, 8);
		assertEquals(false, this.ocean.getShipArray()[4][8].getAlreadySunk());
		destroyer.shootAt(4, 7);
		assertEquals(false, this.ocean.getShipArray()[4][8].getAlreadySunk());
		destroyer.shootAt(4, 7);
		assertEquals(true, this.ocean.getShipArray()[4][8].getAlreadySunk());

		assertEquals(false, this.ocean.getShipArray()[9][9].getAlreadySunk());
		submarine.shootAt(9, 9);
		assertEquals(false, this.ocean.getShipArray()[9][9].getAlreadySunk());
		submarine.shootAt(9, 9);
		assertEquals(true, this.ocean.getShipArray()[9][9].getAlreadySunk());

		emptySea.placeShipAt(1, 4, false, ocean);
		emptySea.shootAt(1, 4);
		assertEquals(false, this.ocean.getShipArray()[1][4].getAlreadySunk());
		emptySea.shootAt(1, 4);
		assertEquals(false, this.ocean.getShipArray()[1][4].getAlreadySunk());

	}

	@Test
	void testSetAlreadySunk() {
		battleship.setAlreadySunk(true);
		assertTrue(battleship.getAlreadySunk());

		cruiser.setAlreadySunk(false);
		assertFalse(cruiser.getAlreadySunk());

		destroyer.setAlreadySunk(true);
		assertTrue(destroyer.getAlreadySunk());

		submarine.setAlreadySunk(true);
		assertTrue(submarine.getAlreadySunk());

		emptySea.setAlreadySunk(false);
		assertFalse(emptySea.getAlreadySunk());

	}

	@Test
	void testGetShipType() {

		//initialize a battleship and place it
		battleship.placeShipAt(0, 3, true, ocean);

		//test getShipType returns the right type
		assertEquals("battleship", this.ocean.getShipArray()[0][1].getShipType());

		//with no ship placement, test empty sea
		assertNotEquals("battleship", this.ocean.getShipArray()[0][4].getShipType());
		assertEquals("empty", this.ocean.getShipArray()[0][4].getShipType());

		//three more cases
		cruiser.placeShipAt(4, 2, true, ocean);
		assertEquals("cruiser",this.ocean.getShipArray()[4][0].getShipType());

		destroyer.placeShipAt(1, 7, true, ocean);
		assertEquals("destroyer",this.ocean.getShipArray()[1][6].getShipType());

		submarine.placeShipAt(9, 9, false, ocean);
		assertEquals("submarine",this.ocean.getShipArray()[9][9].getShipType());


	}

	@Test
	void testOkToPlaceShipAt() {

		assertEquals(true, battleship.okToPlaceShipAt(0, 3, true, ocean));

		//place a battleship
		battleship.placeShipAt(0, 3, true, ocean);

		//test illegal vertically adjacent
		assertEquals(false, cruiser.okToPlaceShipAt(1, 2, true, ocean));

		//test illegal diagonally adjacent
		assertEquals(false, submarine.okToPlaceShipAt(1, 4, false, ocean));

		//test ship out of bounds
		assertEquals(false, destroyer.okToPlaceShipAt(2, 0, true, ocean));

		//test legal placement
		assertEquals(true, emptySea.okToPlaceShipAt(9, 9, true, ocean));

	}

	@Test
	void testPlaceShipAt() {

		//use getShipType to test ship placement
		battleship.placeShipAt(0, 3, true, ocean);
		assertEquals("battleship", this.ocean.getShipArray()[0][1].getShipType());

		cruiser.placeShipAt(4, 2, true, ocean);
		assertEquals("cruiser",this.ocean.getShipArray()[4][0].getShipType());

		destroyer.placeShipAt(6, 6, true, ocean);
		assertEquals("destroyer",this.ocean.getShipArray()[6][5].getShipType());

		submarine.placeShipAt(9, 9, false, ocean);
		assertEquals("submarine",this.ocean.getShipArray()[9][9].getShipType());

		destroyer.placeShipAt(2, 1, false, ocean);
		emptySea.placeShipAt(2, 1, false, ocean);
		assertEquals("destroyer",this.ocean.getShipArray()[1][1].getShipType());
		assertEquals("empty",this.ocean.getShipArray()[2][1].getShipType());

	}

	@Test
	void testShootAt() {

		//places ships
		battleship.placeShipAt(0, 3, true, ocean);
		cruiser.placeShipAt(6, 1, false, ocean);
		destroyer.placeShipAt(4, 8, true, ocean);
		submarine.placeShipAt(9, 9, false, ocean);

		//first shot at battleship
		assertEquals(false, battleship.getAlreadySunk());
		assertEquals(false, battleship.getHit()[1]);
		assertEquals(true, battleship.shootAt(0, 2));
		assertEquals(false, battleship.getAlreadySunk());
		assertEquals(true, battleship.getHit()[1]);

		//second shot at the same location
		assertEquals(true, battleship.shootAt(0, 2));
		assertEquals(false, battleship.getAlreadySunk());
		assertEquals(true, battleship.getHit()[1]);

		//first shot at cruiser
		assertEquals(false, cruiser.getAlreadySunk());
		assertEquals(false, cruiser.getHit()[0]);
		assertEquals(true, cruiser.shootAt(6, 1));
		assertEquals(false, cruiser.getAlreadySunk());
		assertEquals(true, cruiser.getHit()[0]);

		//second shot at the same location
		assertEquals(true, cruiser.shootAt(6, 1));
		assertEquals(false, cruiser.getAlreadySunk());
		assertEquals(true, cruiser.getHit()[0]);

		//third shot at different location
		assertEquals(true, cruiser.shootAt(5, 1));
		assertEquals(false, cruiser.getAlreadySunk());
		assertEquals(true, cruiser.getHit()[1]);
		assertEquals(false, cruiser.isSunk());

		//first shot at destroyer
		assertEquals(false, destroyer.getAlreadySunk());
		assertEquals(false, destroyer.getHit()[0]);
		assertEquals(true, destroyer.shootAt(4, 8));
		assertEquals(false, destroyer.getAlreadySunk());
		assertEquals(true, destroyer.getHit()[0]);

		//second shot at the same location
		assertEquals(true, destroyer.shootAt(4, 8));
		assertEquals(false, destroyer.getAlreadySunk());
		assertEquals(true, destroyer.getHit()[0]);

		//third shot to sink the destroyer
		assertEquals(true, destroyer.shootAt(4, 7));
		assertEquals(false, destroyer.getAlreadySunk());
		assertEquals(true, destroyer.getHit()[1]);
		assertEquals(true, destroyer.isSunk());

		//fourth shot at the same location
		assertEquals(false, destroyer.shootAt(4, 7));
		assertEquals(true, destroyer.getAlreadySunk());
		assertEquals(true, destroyer.getHit()[1]);
		assertEquals(true, destroyer.isSunk());

		//first shot at submarine
		assertEquals(false, submarine.getAlreadySunk());
		assertEquals(false, submarine.getHit()[0]);
		assertEquals(true, submarine.shootAt(9, 9));
		assertEquals(false, submarine.getAlreadySunk());
		assertEquals(true, submarine.getHit()[0]);

		//second shot at the same location
		assertEquals(false, submarine.shootAt(9, 9));
		assertEquals(true, submarine.getAlreadySunk());
		assertEquals(true, submarine.getHit()[0]);

		assertEquals("empty", ocean.getShipArray()[2][7].getShipType());
		emptySea = ocean.getShipArray()[2][7];
		assertEquals(false, emptySea.shootAt(2, 7));

	}

	@Test
	void testIsSunk() {

		battleship.placeShipAt(0, 3, true, ocean);
		battleship.shootAt(0, 2);
		assertEquals(false, battleship.isSunk());
		battleship.shootAt(0, 0);
		battleship.shootAt(0, 1);
		battleship.shootAt(0, 3);
		assertEquals(true, battleship.isSunk());

		cruiser.placeShipAt(6, 1, false, ocean);
		cruiser.shootAt(6, 1);
		assertEquals(false, cruiser.isSunk());
		cruiser.shootAt(5, 1);
		assertEquals(false, cruiser.isSunk());
		cruiser.shootAt(4, 1);
		assertEquals(true, cruiser.isSunk());

		destroyer.placeShipAt(4, 8, true, ocean);
		destroyer.shootAt(4, 8);
		assertEquals(false, destroyer.isSunk());
		destroyer.shootAt(4, 7);
		assertEquals(true, destroyer.isSunk());

		submarine.placeShipAt(5, 5, true, ocean);
		submarine.shootAt(5, 5);
		assertEquals(true, submarine.isSunk());

		assertEquals("empty", ocean.getShipArray()[2][7].getShipType());
		emptySea = ocean.getShipArray()[2][7];
		emptySea.shootAt(2, 7);
		assertEquals(false, emptySea.isSunk());
	}

	@Test
	void testToString() {

		battleship.placeShipAt(0, 3, true, ocean);
		battleship.shootAt(0, 2);
		assertEquals("x", ocean.getShipArray()[0][2].toString());
		battleship.shootAt(0, 0);
		battleship.shootAt(0, 1);
		battleship.shootAt(0, 3);
		assertEquals("s", ocean.getShipArray()[0][0].toString());
		assertEquals("s", ocean.getShipArray()[0][1].toString());
		assertEquals("s", ocean.getShipArray()[0][2].toString());
		assertEquals("s", ocean.getShipArray()[0][3].toString());

		cruiser.placeShipAt(4, 4, false, ocean);
		cruiser.shootAt(4, 4);
		assertEquals("x", ocean.getShipArray()[4][4].toString());
		cruiser.shootAt(3, 4);
		cruiser.shootAt(2, 4);
		assertEquals("s", ocean.getShipArray()[2][4].toString());
		assertEquals("s", ocean.getShipArray()[3][4].toString());
		assertEquals("s", ocean.getShipArray()[4][4].toString());

		destroyer.placeShipAt(5, 3, true, ocean);
		destroyer.shootAt(5, 2);
		assertEquals("x", ocean.getShipArray()[5][2].toString());
		destroyer.shootAt(5, 3);
		assertEquals("s", ocean.getShipArray()[5][3].toString());
		assertEquals("s", ocean.getShipArray()[5][2].toString());

		submarine.placeShipAt(6, 6, true, ocean);
		submarine.shootAt(6, 6);
		assertEquals("s", ocean.getShipArray()[6][6].toString());

		emptySea = ocean.getShipArray()[8][9];
		emptySea.shootAt(8, 9);
		assertEquals("-", ocean.getShipArray()[8][9].toString());

	}

}
