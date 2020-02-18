package hangman;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Describes the characteristics common to both traditional and evil game modes.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
abstract class Hangman {

	//instance variables
	/**
	 * The index of the word in the dictionary.
	 */
	private int wordIndex;

	/**
	 * The randomly picked word.
	 */
	private String firstWord;

	/**
	 * Boolean array representing whether the letter has been guessed out.
	 */
	private boolean[] wordGuessed;
	
	/**
	 * Creates instance of a list of words with the same length.
	 */
	ArrayList<String> sameLengthGroup = new ArrayList<String>();

	//creates instance of Random object
	Random rand = new Random();

	//constructor
	/**
	 * Randomly picks an index of word.
	 * Finds the word in the dictionary.
	 * Initializes the wordGuessed array according to the length of the word.
	 * @param list containing the words
	 */
	public Hangman(ArrayList<String> list) {

		setIndex(list);
		setFirstWord(list);
		this.wordGuessed = new boolean[this.firstWord.length()];
		findSameLength(list);
		
	}
	
	//methods
	//setters and getters
	/**
	 * Picks a random index number.
	 * @param list to pick the word from
	 */
	private void setIndex(ArrayList<String> list) {
		this.wordIndex = rand.nextInt(list.size());
	}

	/**
	 * Gets the index of the word.
	 * @return the index of the word
	 */
	public int getIndex() {
		return this.wordIndex;
	}

	/**
	 * Finds the randomly picked word according to the index number.
	 * @param list to pick the word from
	 */
	public void setFirstWord(ArrayList<String> list) {
		this.firstWord = list.get(this.wordIndex);
	}

	/**
	 * Gets the picked word.
	 * @return the word
	 */
	public String getWord() {
		return this.firstWord;
	}

	/**
	 * Gets the info about whether the letters have been guessed or not
	 * @return the boolean array
	 */
	public boolean[] getWordGuessed() {
		return this.wordGuessed;
	}

	/**
	 * Abstract method giving info about the game mode.
	 * @return the game mode
	 */
	abstract String getGameMode();

	/**
	 * Gets the list of words with the same length.
	 * @return the list
	 */
	public ArrayList<String> getSameLengthGroup(){
		return this.sameLengthGroup;
	}

	/**
	 * Abstract method tells whether the letter has been guessed.
	 * @param letter to search for
	 * @return true or false
	 */
	abstract boolean letterGuessed(char letter);

	/**
	 * Checks whether the game is over.
	 * @return true or false
	 */
	boolean isGameOver() {

		//iterates over the length of the word
		//return false if any letter has been guessed
		//otherwise, return true
		for (int i = 0; i < this.firstWord.length();i++) {
			if (this.getWordGuessed()[i] == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Finds all the words having the same length from the list.
	 * @param list to search
	 */
	private void findSameLength(ArrayList<String> list) {
		
		//iterates over the list
		//finds all the words with the same length as the picked word
		//adds the words to the list
		Iterator<String> word = list.iterator();
		while (word.hasNext()) {

				String nextWord = word.next();
				if (nextWord.length() == this.firstWord.length()) {
					this.sameLengthGroup.add(nextWord);
				}
		}
	}

	/**
	 * Prints the word with unguessed letter shown as "_". 
	 */
	void print() {
		//System.out.println(this.firstWord);
		//creates instance of list
		List<String> letters = new ArrayList<String>();

		//iterates over the length
		//adds the revealed letter to the list
		//adds "_" if the letter has not been guessed yet
		//joins the letters and prints the word
		for (int i = 0; i < this.firstWord.length(); i++) {
			if (this.getWordGuessed()[i] == false) {
				letters.add("_");
			}else {
				letters.add(Character.toString(this.firstWord.charAt(i)));
			}
		}
		System.out.println(String.join(" ", letters));
	}
}
