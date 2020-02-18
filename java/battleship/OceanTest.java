package battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Ocean class
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
class OceanTest {

	/**
	 * Creates new Ocean object.
	 */
	Ocean ocean;

	@BeforeEach
	void setUp() throws Exception {

		//initializes the instance object
		this.ocean = new Ocean();

	}

	@Test
	void testOcean() {

		//default ocean filled with EmptySea objects
		assertEquals(this.ocean.getShipArray()[0][0].getShipType().toString(), "empty");
		assertEquals(this.ocean.getShipArray()[5][3].getShipType().toString(), "empty");

		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			this.ocean.getShipArray()[10][3].getShipType();
		});

		//default values for game variables
		assertEquals(this.ocean.getHitCount(), 0);
		assertEquals(this.ocean.getShotsFired(), 0);
		assertEquals(this.ocean.getShotsFired(), 0);

	}

	@Test
	void testIsOccupied() {

		//default ocean filled with EmptySea objects
		assertEquals(false, this.ocean.isOccupied(0, 0));

		//fills with one battleship
		this.ocean.getShipArray()[2][4] = new Battleship();

		//checks occupation
		assertEquals(true, this.ocean.isOccupied(2, 4));

		//fills with one cruiser
		this.ocean.getShipArray()[3][4] = new Cruiser();

		//checks occupation
		assertEquals(true, this.ocean.isOccupied(3, 4));

	}

	@Test
	void testShootAt() {

		//default values for game variables
		assertEquals(0, this.ocean.getShotsFired());
		assertEquals(0, this.ocean.getHitCount());

		//first shot
		assertEquals(false, this.ocean.shootAt(0, 0));
		assertEquals(1, this.ocean.getShotsFired());
		assertEquals(0, this.ocean.getHitCount());

		//fills with one real ship
		this.ocean.getShipArray()[0][0] = new Submarine();

		//second shot
		assertEquals(true, this.ocean.shootAt(0, 0));
		assertEquals(1, this.ocean.getHitCount());
		assertEquals(2, this.ocean.getShotsFired());

		//initialize a new destroyer
		Ship destroyer = new Destroyer();
		destroyer.placeShipAt(1, 0, false, this.ocean);

		//shoot but not sink, number of shipsSunk is 0
		destroyer.shootAt(0, 0);
		ocean.shootAt(0, 0);
		assertEquals(0, this.ocean.getShipsSunk());

		//sink the ship, number of shipsSunk is 1
		destroyer.shootAt(1,0);
		ocean.shootAt(1, 0);
		assertEquals(1, this.ocean.getShipsSunk());
	}

	@Test
	void testGetShotsFired() {

		//default value for shotsFired
		assertEquals(0, this.ocean.getShotsFired());

		//updated value after one shot
		this.ocean.shootAt(0, 0);
		assertEquals(1, this.ocean.getShotsFired());

		//updated value for two shots at the same place
		this.ocean.shootAt(0, 0);
		assertEquals(2, this.ocean.getShotsFired());

		//updated value for three shots at different places
		this.ocean.shootAt(1, 0);
		assertEquals(3, this.ocean.getShotsFired());

	}

	@Test
	void testGetHitCount() {
		//default value for hitCount
		assertEquals(0, this.ocean.getHitCount());

		//fills with one destroyer
		Ship ship = new Destroyer();
		ship.placeShipAt(3, 0, false, this.ocean);

		//updated value after one shot without hitting a real ship
		this.ocean.shootAt(0, 0);
		ship.shootAt(0, 0);
		assertEquals(0, ocean.getHitCount());

		//first shot at new location
		//updates the ship condition
		this.ocean.shootAt(3, 0);
		ship.shootAt(3, 0);

		//updated value after one shot with a real ship hit but not sunk
		assertEquals(1, this.ocean.getHitCount());

		//second shot at the same location with a real ship hit but not sunk
		this.ocean.shootAt(3, 0);
		ship.shootAt(3, 0);
		assertEquals(2, this.ocean.getHitCount());

		//third shot at different location
		this.ocean.shootAt(2, 0);
		ship.shootAt(2, 0);
		assertEquals(3, this.ocean.getHitCount());

		//another shot at the same location with a sunk ship
		this.ocean.shootAt(2, 0);
		assertEquals(4, this.ocean.getHitCount());

	}

	@Test
	void testGetShipsSunk() {

		//default value for sunk ships
		assertEquals(0, this.ocean.getShipsSunk());

		//fills with one submarine and shoots it
		Ship ship = new Cruiser();
		ship.placeShipAt(6, 6, true, this.ocean);
		ship.shootAt(6, 5);
		this.ocean.shootAt(6, 5);

		//updated value for total number of sunk ships
		assertEquals(0, this.ocean.getShipsSunk());

		//fills with one submarine and shoots it sunk
		ship = new Submarine();
		ship.placeShipAt(0, 0, true, this.ocean);
		ship.shootAt(0, 0);
		this.ocean.shootAt(0, 0);

		//updated value for total number of sunk ships
		assertEquals(1, this.ocean.getShipsSunk());

	}

	@Test
	void testIsGameOver() {

		//test based on default ocean
		assertEquals(false, this.ocean.isGameOver());

		//fills new destroyer ship
		//shoots the ship
		Ship ship = new Destroyer();
		ship.placeShipAt(5, 6, false, this.ocean);
		ship.shootAt(5, 6);
		this.ocean.shootAt(5, 6);

		//updated result
		assertEquals(false, this.ocean.isGameOver());

		//fills the ocean with 10 submarine ships
		//shoots to sunk
		for (int i = 0; i < 10; i++) {
			ship = new Submarine();
			ship.placeShipAt(i, 0, false, this.ocean);
			ship.shootAt(i, 0);
			this.ocean.shootAt(i, 0);
		}

		//updated result
		assertEquals(true, this.ocean.isGameOver());

	}

	@Test
	void testGetShipArray() {

		assertEquals(10, this.ocean.getShipArray().length);
		assertEquals(10, this.ocean.getShipArray()[0].length);

	}

}
