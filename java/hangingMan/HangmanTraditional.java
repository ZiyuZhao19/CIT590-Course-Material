package hangman;

import java.util.ArrayList;

/**
 * Represents a subclass of Hangman, a case of traditional hangman.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
class HangmanTraditional extends Hangman {
	
	//static variable
	/**
	 * Represents the game mode.
	 */
	private static final String MODE = "traditional";
	
	//instance variables
	/**
	 * Randomly picked word inherited from superclass Hangman.
	 */
	String firstWord = super.getWord();

	//constructor
	/**
	 * Creates traditional game using words from the list.
	 * @param list representing word dictionary
	 */
	public HangmanTraditional(ArrayList<String> list) {
		//calls the constructor in superclass Hangman 
		super(list);
	}
	
	//methods
	/**
	 * Checks whether the given letter is in the picked word.
	 * Return false if the letter has already been entered.
	 */
	@Override
	boolean letterGuessed(char letter) {
		
		//sets guessed as false
		boolean guessed = false;
		
		//iterates over the length of the word
		//if the letter of the word has not been guessed,
		//and if the given letter matches that unguessed letter,
		//marks the letter and sets guessed as true.
		//returns the guessed boolean
		for (int i = 0; i < this.firstWord.length();i++) {

			if (this.getWordGuessed()[i] == false) {
				if ((this.firstWord.charAt(i) == letter)){
					guessed = true;
					this.getWordGuessed()[i] = true;
					
				}else {
					this.getWordGuessed()[i] = false;
					
				}
			}
		}
		return guessed;
	}
	
	/**
	 * Returns the information of the game mode.
	 */
	@Override
	public String getGameMode() {
		return HangmanTraditional.MODE;
	}

}
