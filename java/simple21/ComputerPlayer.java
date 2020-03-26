package simple21;

/**
 * Ziyu Zhao
 * 71435169
 */

/**
 * Represents a computer player in this simplified version of the "21" card game.
 */
public class ComputerPlayer {

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
	 * Constructs a computer player with the given name.
	 * @param name of the user.
	 */
	public ComputerPlayer (String name) {
		this.name = name;
	}

	/**
	 * Decides whether to take another card. In order to make this decision, this player considers 
	 * their own total points (sum of visible cards + hidden card). 
	 * This player may also consider other players' sum of visible cards, but not the value 
	 * of other players' hidden cards.
	 * @param human The other human player
	 * @param player1 Another (computer) player
	 * @param player2 Another (computer) player
	 * @param player3 Another (computer) player
	 * @return true if this player wants another card
	 */
	public boolean offerCard(HumanPlayer human, ComputerPlayer player1, ComputerPlayer player2, ComputerPlayer player3) { 
		// let the computer player chooses to pass once its score reaches 17
		// returns the boolean of whether pass or not
		if (this.getScore() > 17) {
			passed = true;
		} else {
			passed = false;
		}
		return passed;
	}

	/**    
	 * Puts the specified card in this player's hand as the hidden card.
	 * Prints a message saying that the card is being taken, but does not print the value of the hidden card.
	 * @param card being taken
	 */
	public void takeHiddenCard(int card) {
		// for the hidden pile to take one card
		this.hiddenCard += card;
		// print hidden card taken
		System.out.println(this.name + " takes a hidden card.");
	}

	/**
	 * Adds the given card to the sum of the visible cards for this player.
	 * Prints a message saying that the card is being taken.
	 * @param card being taken
	 */
	public void takeVisibleCard(int card) { 
		// deal a card to the visible pile
		// add the score to the sum
		this.sumOfVisibleCards += card;
		// print a message
		System.out.println(this.name + " takes a " + card + ".");
	}

	/**
	 * Returns the total sum of this player's cards, not counting the hidden card. 
	 * @return sumOfVisibleCards
	 */
	public int getSumOfVisibleCards() { 
		// return the sum of visible cards
		return this.sumOfVisibleCards;	
	}

	/**
	 * Return this player's total score (the total of all this player's cards).
	 * That is to say, the sum of the visible cards + the hidden card.
	 * @return total score 
	 */
	public int getScore() { 
		// sum the visible cards and hidden cards
		return this.hiddenCard + this.sumOfVisibleCards;
	}

	/**
	 * Calculate the difference between the player's score and 21
	 * To evaluate how close it is to 21
	 * @return the difference between score and 21
	 */
	public int howClose() {
		// create an integer to store the value of differences
		int difference = 21-this.getScore();
		return difference;
	}
}

