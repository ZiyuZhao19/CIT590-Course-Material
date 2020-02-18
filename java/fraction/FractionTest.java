package hw7;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the functions in Fraction Class with specific cases.
 * @author Ziyu Zhao
 *
 */
class FractionTest {
	
	//declare fractions for testing
	Fraction fraction;
	Fraction normal1;
	Fraction normal2;
	Fraction notReduced;
	Fraction negativeDenominator;
	Fraction zeroNumerator;
	
	//initialize the fractions
	@BeforeEach
	void setUp() throws Exception{
		normal1 = new Fraction(2,3);
		normal2 = new Fraction(4,5);
		notReduced = new Fraction(4,16);
		negativeDenominator = new Fraction(10,-15);
		zeroNumerator = new Fraction(0,-4);
	}

	@Test
	void testFraction() {
		//to test if Fraction creates equal fractions with same parameters
		assertEquals(normal1, new Fraction(4,6));
		assertEquals(zeroNumerator, new Fraction(0,-16));
		//use toString to check if the fractions are shown as expected
		assertEquals("-4/16", new Fraction(4,-16).toString());
		assertEquals("4/16", new Fraction(-4,-16).toString());
	}

	@Test
	void testReduceToLowestForm() {
		//reduce the fractions to their lowest forms
		notReduced.reduceToLowestForm();
		negativeDenominator.reduceToLowestForm();
		zeroNumerator.reduceToLowestForm();
		
		//test not reduced fractions
		assertEquals(notReduced, new Fraction(2,8));
		//test negative fractions
		assertEquals(new Fraction(-3,12), new Fraction(6,-24));
		//test zero-numerator fractions
		assertEquals("0/1", zeroNumerator.toString());
	}

	@Test
	void testAdd() {
		assertEquals("22/15", normal1.add(normal2).toString());
		fraction = new Fraction(2,-3);
		//test zero numerator
		assertEquals(new Fraction(0,1), fraction.add(normal1));
		//test negative
		assertEquals("-4/3", fraction.add(negativeDenominator).toString());
		//use boolean equals to test
		assertTrue(fraction.add(new Fraction(9,3)).equals(new Fraction(1,1).add(new Fraction(4,3))));
	}

	@Test
	void testSubtract() {
		//subtract the given
		assertEquals(new Fraction(-2,15), normal1.subtract(normal2));
		fraction = new Fraction(2,-3);
		//test zero
		assertEquals(new Fraction(0,1), fraction.subtract(negativeDenominator));
		//test negative
		assertEquals("-4/3", fraction.subtract(normal1).toString());
		//use boolean equals to test
		assertTrue(fraction.equals(new Fraction(2,3).subtract(new Fraction(4,3))));
	}

	@Test
	void testMul() {
		fraction = new Fraction(1,2);
		//test regular multiplication
		assertEquals(new Fraction(8,15), normal1.mul(normal2));
		fraction = new Fraction(2,-3);
		//test zero numerator
		assertEquals("0/1", fraction.mul(zeroNumerator).toString());
		//test negative
		assertEquals(new Fraction(-4,9), fraction.mul(normal1));
	}

	@Test
	void testDiv() {
		assertEquals(new Fraction(5,6), normal1.div(normal2));
		fraction = new Fraction(2,-3);
		//test negatives
		assertEquals("-5/6", fraction.div(normal2).toString());
		assertEquals("-1/1", fraction.div(normal1).toString());
		//test the zero case
		assertEquals("0/1", zeroNumerator.div(notReduced).toString());
	}

	@Test
	void testDecimal() {
		//test decimal with delta
		assertEquals(normal1.decimal(), 0.666666666, 0.00000001);
		assertEquals(zeroNumerator.decimal(), 0, 0.01);
		fraction = new Fraction(4,7);
		assertEquals(fraction.decimal(), 0.57142857, 0.00000001);
	}

	@Test
	void testSqr() {
		//test negative
		negativeDenominator.sqr();
		assertEquals(new Fraction(4,9), negativeDenominator);
		//test not reduced
		notReduced.sqr();
		assertEquals(new Fraction(1,16), notReduced);
		//test 0
		zeroNumerator.sqr();
		assertEquals("0/1",zeroNumerator.toString());
	}

	@Test
	void testAverageFraction() {
		assertEquals(new Fraction(11,15), normal2.average(normal1));
		fraction = new Fraction(2,-3);
		assertEquals("0/1", fraction.average(normal1).toString());
		assertEquals(new Fraction(-1,3), fraction.average(zeroNumerator));
	}

	@Test
	void testAverageFractionArray() {
		Fraction[] myArrayOfFractions1 = {new Fraction(3,4), new Fraction(3,5), new Fraction(3,6)};
		Fraction f1 = Fraction.average(myArrayOfFractions1);
		assertEquals(new Fraction(37,60), f1);
		Fraction[] myArrayOfFractions2 = {};
		//test the form of 0
		Fraction f2 = Fraction.average(myArrayOfFractions2);
		assertEquals("0/1", f2.toString());
		//test negative
		Fraction[] myArrayOfFractions3 = {new Fraction(-1,24), new Fraction(2,-24), new Fraction(5,-24)};
		Fraction f3 = Fraction.average(myArrayOfFractions3);
		assertEquals("-1/9", f3.toString());
	}

	@Test
	void testAverageIntArray() {
		int[] myArrayOfInts1 = {1,2,3,4,5};
		Fraction f1 = Fraction.average(myArrayOfInts1);
		assertEquals("3/1", f1.toString());
		//test the form of 0
		int[] myArrayOfInts2 = {};
		Fraction f2 = Fraction.average(myArrayOfInts2);
		assertEquals("0/1", f2.toString());
		int[] myArrayOfInts3 = {1,2,3,4};
		Fraction f3 = Fraction.average(myArrayOfInts3);
		assertTrue(new Fraction(5,2).equals(f3));
	}

	@Test
	void testEqualsObject() {
		assertTrue(normal1.equals(new Fraction(4,6)));
		//test not reduced equals to reduced
		assertTrue(notReduced.equals(new Fraction(1,4)));
		//test 0 numerator
		assertTrue(zeroNumerator.equals(new Fraction(0,-100000)));
	}

	@Test
	void testToString() {
		assertEquals("2/3", normal1.toString());
		//test if the minus symbol is at the right place
		assertEquals("-10/15", negativeDenominator.toString());
		//test if the zero fraction remains the same without calling the reduce function
		assertEquals("0/4", zeroNumerator.toString());
	}
}
