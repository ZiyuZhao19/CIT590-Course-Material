package hw7;

/**
 * Represents a fraction by a numerator and a denominator.
 * @author Ziyu Zhao
 *
 */
public class Fraction {
	
	//Create instance variables
	
	/**
	 * Integers for numerator and denominator.
	 */
	int numerator;
	int denominator;
	
	/**
	 * Create a fraction with given numerator and denominator.
	 * For negative fractions, make sure the minus goes with the numerator.
	 * @param numerator
	 * @param denominator
	 */
	public Fraction(int numerator, int denominator) {
		//if the fraction is negative (or zero), pass the minus symbol to the numerator
		if ((numerator <= 0 && denominator > 0) || (numerator >= 0 && denominator < 0)) {
			this.numerator = -Math.abs(numerator);
			this.denominator = Math.abs(denominator);
		}else{
			//if the the numerator and denominator are both greater or smaller than 0
			//remove all the minus symbol
			this.numerator = Math.abs(numerator);
			this.denominator = Math.abs(denominator);
		}
	}
	
	/**
	 * Reduce the current fraction by eliminating common factors.
	 * 0 is represented by 0/1.
	 */
	public void reduceToLowestForm() {
		//set up the variable to store the biggest common factor
		int commonFactor = 1;
		//initialize instance variable, reducing 0 to 0/1
		if (this.numerator == 0) {
			this.denominator = 1;
		}else {
			//iterate over the possible common factors and take the value of the last one
			for (int i = 1; i <= Math.max(this.numerator, this.denominator); i++) {
				if (this.numerator%i == 0 && this.denominator%i == 0) {
					commonFactor = i;
				}
			}
			//divide both numerator and denominator by the biggest common factor
			this.numerator/=commonFactor;
			this.denominator/=commonFactor;
		}
	}
	
	/**
	 * Add the current fraction to the given otherFraction.
	 * Returns a new Fraction that is the sum of the two Fractions.
	 * @param otherFraction
	 * @return
	 */
	public Fraction add(Fraction otherFraction) {
		//the added numerator
		int newNumerator = this.numerator*otherFraction.denominator + otherFraction.numerator*this.denominator;
		//the added denominator
		int newDenominator = this.denominator*otherFraction.denominator;
		//a new fraction to store the added value
		Fraction newFrac = new Fraction(newNumerator, newDenominator);
		//reduce the fraction to its lowest form
		newFrac.reduceToLowestForm();
		return newFrac;
	}
	
	/**
	 * Subtract the given otherFraction from the current fraction.
	 * Returns a new Fraction that is the difference of the two Fractions.
	 * @param otherFraction
	 * @return
	 */
	public Fraction subtract(Fraction otherFraction) {
		//numerator after the subtraction
		int newNumerator = this.numerator*otherFraction.denominator - otherFraction.numerator*this.denominator;
		//denominator after the subtraction
		int newDenominator = this.denominator*otherFraction.denominator;
		
		//a new fraction to store the subtracted value
		Fraction newFrac = new Fraction(newNumerator, newDenominator);
		//reduce the fraction to its lowest form
		newFrac.reduceToLowestForm();
		return newFrac;
	}
	
	/**
	 * Multiply the current fraction by the given otherFraction.
	 * Returns a new Fraction that is the product of this fraction and the otherFraction.
	 * @param otherFraction
	 * @return
	 */
	public Fraction mul(Fraction otherFraction) {
		//numerator and denominator after the multiplication
		int newNumerator = this.numerator*otherFraction.numerator;
		int newDenominator = this.denominator*otherFraction.denominator;
		//a new fraction and reduce it to its lowest form
		Fraction newFrac = new Fraction(newNumerator, newDenominator);
		newFrac.reduceToLowestForm();
		return newFrac;
	}
	
	/**
	 * Divide the current fraction by the given otherFraction.
	 * Returns a new Fraction that is the quotient of this fraction and the otherFraction.
	 * @param otherFraction
	 * @return
	 */
	public Fraction div(Fraction otherFraction) {
		//numerator and denominator after the division
		int newNumerator = this.numerator*otherFraction.denominator;
		int newDenominator = this.denominator*otherFraction.numerator;
		//a new fraction and reduce it to its lowest form
		Fraction newFrac = new Fraction(newNumerator, newDenominator);
		newFrac.reduceToLowestForm();
		return newFrac;
	}
	
	/**
	 * Return this fraction in decimal form.
	 * @return
	 */
	public double decimal() {
		//cast the double type to the result of dividing the numerator by denominator
		double fracDecimal = (double) this.numerator/this.denominator;
		return fracDecimal;
	}
	
	/**
	 * Square the current fraction.
	 */
	public void sqr() {
		//modifies the current fraction and reduces it to its lowest
		this.numerator = this.numerator*this.numerator;
		this.denominator = this.denominator*this.denominator;
		this.reduceToLowestForm();
	}
	
	/**
	 * Average the current fraction with the given otherFraction.
	 * Return a new Fraction that is the average of this fraction and the otherFraction.
	 * @param otherFraction
	 * @return
	 */
	public Fraction average(Fraction otherFraction) {
		//add otherFraction to thisFraction and divide the result by 2
		int newNumerator = this.add(otherFraction).numerator;
		int newDenominator = this.add(otherFraction).denominator*2;
		
		//a new fraction to store the result and reduce to its lowest
		Fraction newFrac = new Fraction(newNumerator, newDenominator);
		newFrac.reduceToLowestForm();
		return newFrac;
	}
	
	/**
	 * Average all of the fractions in the given array.
	 * Return the average of the array.
	 * @param fractions
	 * @return
	 */
	public static Fraction average(Fraction[] fractions){
		//two local variables to store the new numerator and denominator
		int totalNumerator = 0;
		int totalDenominator = 1;
		//create a new fraction to store the process value
		Fraction aveFrac = new Fraction(totalNumerator, totalDenominator);
		
		//if the array is not empty, iterate over the array, adding the fractions together
		if (fractions.length > 0) {
			for (int i = 0; i < fractions.length; i++) {
				aveFrac = aveFrac.add(fractions[i]);
				totalNumerator = aveFrac.numerator;
				totalDenominator = aveFrac.denominator;
			}
			//calculate the average by multiply the denominator to the number of elements
			totalDenominator *= fractions.length;
		}
		
		//create a new fraction to store the final average
		Fraction averageArr = new Fraction(totalNumerator, totalDenominator);
		//reduce the fraction
		averageArr.reduceToLowestForm();
		return averageArr;
	}
	
	/**
	 * Average all the integers in the given array.
	 * Return the average of the array as a new Fraction.
	 * @param ints
	 * @return
	 */
	public static Fraction average(int[] ints) {
		//two local variables to store the new numerator and denominator
		int aveIntNumerator = 0;
		int aveIntDenominator = 1;
		//an integer variable to store the total amount
		int totalInts = 0;
		
		//if the array is not empty
		//iterate over the array to calculate the total
		if (ints.length > 0) {
			for (int i = 0; i < ints.length; i++) {
				totalInts += ints[i];
			}
			aveIntNumerator = totalInts;
			aveIntDenominator = ints.length;
		}
		
		//create a new fraction to store the result and reduce it to lowest
		Fraction aveInts = new Fraction(aveIntNumerator, aveIntDenominator);
		aveInts.reduceToLowestForm();
		return aveInts;
	}
	
	/**
	 * Compare the given object (as a fraction) to the current fraction, for equality.
	 */
	@Override
	public boolean equals(Object object) {
		//cast the fraction type to the object
		Fraction toTest = (Fraction) object;
		//clone it to reduce
		Fraction toTestClone = new Fraction(toTest.numerator,toTest.denominator);
		//clone thisFraction to reduce
		Fraction thisClone = new Fraction(this.numerator, this.denominator);
		//reduce the cloned fractions for comparison
		toTestClone.reduceToLowestForm();
		thisClone.reduceToLowestForm();
		
		//if the numerators and the denominators are equal, return true
		//else, return false as not equal
		if (thisClone.numerator == toTestClone.numerator) {
			if (thisClone.denominator == toTestClone.denominator) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return a string representation of the current fraction.
	 */
	@Override
	public String toString() {
		//create a string variable to store the info of a fraction
		String fraction_str = (this.numerator + "/" + this.denominator);
		return fraction_str;
	}
}