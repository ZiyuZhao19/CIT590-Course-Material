package simple21;

import java.util.Scanner;

/**
 * Ziyu Zhao
 * 71435169
 */

/**
 * Represents a human player in a game of Simple 21.
 */
public class HumanPlayer {

	/** 
	 * The name of the player.
	 */
	String name;

	/**
	 * The player's one hidden card (a value from 1 - 10).
	 */
	private int hiddenCard = 0;

	/** 
	 * The sum of the player's cards, not counting the hidden card. 
	 */
	private int sumOfVisibleCards = 0;

	/**
	 * Flag indicating if the player has passed (asked for no more cards).
	 */
	boolean passed = false;

	/**
	 * Constructs a human player with the given name.
	 * @param name of the user.
	 */
	public HumanPlayer(String name) {
		this.name = name;
	}

	/**
	 * Asks the Human player whether to take another card and uses the given scanner to prompt for a response.
	 * @param human This human player
	 * @param player1 Another (computer) player
	 * @param player2 Another (computer) player
	 * @param player3 Another (computer) player
	 * @param scanner To use for scanning for human input
	 * @return true if this human player wants another card
	 */
	public boolean offerCard(HumanPlayer human, ComputerPlayer player1, ComputerPlayer player2, ComputerPlayer player3, Scanner scanner) {
		boolean response = true;

		this.showCards(this, player1, player2, player3);

		response = this.getYesOrNoToQuestion("Take another card?", scanner);
		if (!response) this.passed = true;

		return response;
	}

	/**
	 * Prints the sum of all of this Human's cards, and the sum of each of the other (computer) players' 
	 * visible cards.
	 * @param human This human player
	 * @param player1 Another (computer) player
	 * @param player2 Another (computer) player
	 * @param player3 Another (computer) player
	 */
	public void showCards(HumanPlayer human, ComputerPlayer player1, ComputerPlayer player2, ComputerPlayer player3) {
		// creating the record board
		// creating the header of the record board
		String toShow = ("    --------" + "Current Record" +"--------");
		toShow += "\n\tName\t\tPoints";

		// print the sum of human player's total score
		toShow += "\n\t" + human.name+ "\t\tTotal:" + (human.hiddenCard + human.sumOfVisibleCards);

		// print each of the computer player's total of visible cards
		toShow += "\n\t" + player1.name + "\tVisible:" + (player1.getSumOfVisibleCards());
		toShow += "\n\t" + player2.name + "\tVisible:" + (player2.getSumOfVisibleCards());
		toShow += "\n\t" + player3.name + "\tVisible:" + (player3.getSumOfVisibleCards()) + "\n";

		// print the record board
		System.out.print(toShow);
	}

	/**
	 * Displays the given question and prompts for user input using the given scanner.
	 * @param question to ask
	 * @param scanner to use for user input
	 * @return true if the user inputs 'y'
	 */
	public boolean getYesOrNoToQuestion(String question, Scanner scanner) {
		boolean response = true;

		String answer;

		System.out.println();
		System.out.print(question + " ");

		while(true) {
			answer = scanner.next();

			if (answer.toLowerCase().charAt(0) == 'y') {
				response = true;
				break;
			} else if (answer.toLowerCase().charAt(0) == 'n') {
				response = false;
				break;
			}
		}

		return response;
	}

	/**    
	 * Puts the specified card in this human's hand as the hidden card.
	 * Prints a message saying that the card is being taken, and prints the value of the hidden card.
	 * @param card being taken
	 */
	public void takeHiddenCard(int card) {
		// deal a hidden card to the human player
		this.hiddenCard += card;
		System.out.println(this.name + " takes a hidden card.");
		// let the human player know what the hidden card is
		System.out.println("(It's a " + card + ")");
	}

	/**
	 * Adds the given card to the sum of the visible cards for this human player.
	 * Prints a message saying that the card is being taken.
	 * @param card being taken
	 */
	public void takeVisibleCard(int card) { 
		// deal a visible card to the player
		this.sumOfVisibleCards += card;
		// print a message
		System.out.println(this.name + " takes a " + card);
	}

	/**
	 * Returns the total sum of this player's cards, not counting the hidden card. 
	 * @return sumOfVisibleCards
	 */
	public int getSumOfVisibleCards() { 
		// return the sum of all the visible cards
		return this.sumOfVisibleCards; 	
	}

	/**
	 * Return this player's total score (the total of all this human player's cards).
	 * That is to say, the sum of the visible cards + the hidden card.
	 * @return total score 
	 */
	public int getScore() { 
		// print total sum of the visible cards and hidden cards
		return this.hiddenCard + this.sumOfVisibleCards;
	}

	/**
	 * Calculate the difference between the player's score and 21
	 * To evaluate how close it is to 21
	 * @return the difference between score and 21
	 */
	public int howClose() {
		// create an integer to store the value of human player's difference
		int difference = 21-this.getScore();
		return difference;
	}
}
