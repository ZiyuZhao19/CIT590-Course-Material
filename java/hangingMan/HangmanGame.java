package hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Main class for a Human vs Computer version of Hangman Game.
 * Creates cases of word guesses
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
public class HangmanGame {

	/**
	 * print instruction for users
	 */
	private static void printInstructions() {

		System.out.println("Welcome to Hangman Game! "
				+ "\nIn this game, you make guesses to a mystery word. "
				+ "\nFor each guess, you enter a character as a guess of characters in the word."
				+ "\nIf you successfully guess a word, it will be displayed."
				+ "\nIf your guess is not a match, the characters in the word remain mysteries."
				+ "\nTry as few guesses as you can to spell the word!"
				+ "\nEnjoy! Good luck!");
		System.out.println();
	}

	/**
	 * main method
	 * set up the game
	 * set up the scanner and take user input
	 * read the file and build word dictionary
	 * implement the game 
	 * @param args the main function
	 */
	public static void main(String[] args) {

		//print instructions
		HangmanGame.printInstructions();

		//set up the scanner
		Scanner scanner = new Scanner(System.in);

		//set up a string variable to store letters
		String letter = "";

		//set up a character variable to store letters
		char letterChar = ' ';

		//set up a boolean variable to store player's intention to play again
		boolean playAgain = true;

		//read and clean the file and return the ArrayList with words from the file
		ArrayList<String> words = BuildDictionary.getWords("C:\\Users\\Administrator\\eclipse-workspace\\HW9\\src\\hangman\\words_clean.txt");

		//ArrayList<String> words = BuildDictionary.getWords("C:\\Users\\Administrator\\eclipse-workspace\\HW9\\src\\hangman\\words.txt");

		//ArrayList<String> finalWords = BuildDictionary.cleanWords(words);

		//loop while the player wants to pay again
		while(playAgain == true) {

			//instantiate HangmanTraditional
			Hangman hangman = new HangmanTraditional(words);
			//Hangman hangman = new HangmanTraditional(finalWords);


			//choose randomly between the traditional version and evil version
			Random rand = new Random();
			int mode = rand.nextInt(2);
			if (mode == 1) {
				hangman = new HangmanEvil(words);
				//hangman = new HangmanEvil(finalWords);
			}

			//TO DELETE
			//System.out.println("Game version: " + hangman.getGameMode());
			//System.out.println(hangman.getSameLengthGroup());

			//print the initialized word
			hangman.print();

			//keep count of player's guesses
			int guessesCount = 0;

			//keep record of player's wrong guesses
			List<Character> wrongGuesses = new ArrayList<Character>();

			//keep record of all the player's guesses both right and wrong
			List<Character> allGuesses = new ArrayList<Character>();

			//while the conditions to end the game are not met, continue to take user input
			while (!hangman.isGameOver()) {

				System.out.println("Please enter a letter");

				//scan user input
				if (scanner.hasNext()) {
					letter = scanner.next().toLowerCase();
					letterChar = letter.charAt(0);
				}

				//if the input is not a letter, ask the player to enter again
				while (!Character.isLetter(letterChar)) {
					System.out.println("Please enter an alphabetic letter");
					letter = scanner.next().toLowerCase();
					letterChar = letter.charAt(0);
				}

				//if the player makes repeated guesses, send her a message and ask for another input
				while (allGuesses.contains(letterChar)) {
					System.out.println("Already entered, please try another letter");
					letter = scanner.next().toLowerCase();
					letterChar = letter.charAt(0);
				}

				//record legal guesses
				allGuesses.add(letterChar);

				//if the guess is not successful
				//add the guess to the wrongGuesses list
				if(!hangman.letterGuessed(letterChar)) {
					wrongGuesses.add(letterChar);
				}

				//if there are wrong guesses, print them out
				if (wrongGuesses.size() != 0) {
					System.out.println("Wrong Guesses: " + wrongGuesses);
				}

				//print the state of the word
				System.out.println();
				hangman.print();

				//increment the guesses' count
				guessesCount += 1;
			}

			//print final results
			System.out.println("The game is over.");
			System.out.println("Guesses Count: " + guessesCount);
			System.out.println("Game version: " + hangman.getGameMode());

			System.out.println("Wanna play again (y/n)");

			//read user's input
			String answer = scanner.next();

			//check the validity of user input
			while (answer.charAt(0) != 'n' && answer.charAt(0) != 'N' 
					&& answer.charAt(0) != 'y' && answer.charAt(0) != 'Y') {
				System.out.println("Please enter y/n");
				answer = scanner.nextLine();
			}

			//if user's response starts with n, kill the game
			//if user's response starts with y, start another round
			if (answer.charAt(0) == 'n' || answer.charAt(0) == 'N') {
				playAgain = false;
				System.out.println("Goodbye!");
			}
		}
		scanner.close();
	}
}
