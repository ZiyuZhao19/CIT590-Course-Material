package simple21;

import java.util.Scanner;

import java.util.Random;

/**
 * Ziyu Zhao
 * 71435169
 * referred to Easy Steps to Java
 */

/**
 * This is a simplified version of a common card game, "21". 
 */
public class GameControl {

	/**
	 * Human player.
	 */
	HumanPlayer human;

	/**
	 * Computer player.
	 */
	ComputerPlayer player1;

	/**
	 * Computer player.
	 */
	ComputerPlayer player2;

	/**
	 * Computer player.
	 */
	ComputerPlayer player3;

	/** 
	 * A random number generator to be used for returning random "cards" in a card deck.
	 * */
	Random random = new Random();

	/**
	 * The main method just creates a GameControl object and calls its run method.
	 * @param args Not used.
	 */
	public static void main(String args[]) {    
		new GameControl().run();
	}

	/**
	 * Prints a welcome method, then calls methods to perform each of the following actions:
	 * - Create the players (one of them a Human)
	 * - Deal the initial two cards to each player
	 * - Control the play of the game
	 * - Print the final results
	 */
	public void run() {
		// print welcome and introduction
		System.out.println("Welcome to Simple 21! "
				+ "\nYou will play against three computer players. "
				+ "\nTry to get as close to 21 as possible, without going over."
				+ "\nHave fun!"
				+ "\nWhat's your name? ");
		// scan input as human player's name
		Scanner scanner = new Scanner(System.in);
		String humansName = scanner.next();

		// create players
		createPlayers(humansName);
		// deal initial bricks
		deal();

		// creating a while loop, to loop until all of the players pass
		while (checkAllPlayersHavePassed() == false) {
			// call controlPlay
			controlPlay(scanner);
			System.out.println();
		}

		// print result of scores
		printResults();
		// print winner announcement
		printWinner(); 
		scanner.close();
	}

	/**
	 * Creates one human player with the given humansName, and three computer players with hard-coded names.
	 * @param humansName for human player
	 */
	public void createPlayers(String humansName) {
		// use constructor function to create players with names
		//human
		human = new HumanPlayer(humansName);
		//3 computer players
		player1 = new ComputerPlayer("Player 1");
		player2 = new ComputerPlayer("Player 2");
		player3 = new ComputerPlayer("Player 3");

	}

	/**
	 * Deals two "cards" to each player, one hidden, so that only the player who gets it knows what it is, 
	 * and one face up, so that everyone can see it. (Actually, what the other players see is the total 
	 * of each other player's cards, not the individual cards.)
	 */
	public void deal() { 
		// deal two initial cards
		// deal human cards
		human.takeHiddenCard(nextCard());
		human.takeVisibleCard(nextCard());

		// deal computer cards
		// computer player 1
		player1.takeHiddenCard(nextCard());
		player1.takeVisibleCard(nextCard());

		// computer player 2
		player2.takeHiddenCard(nextCard());
		player2.takeVisibleCard(nextCard());

		// computer player 3
		player3.takeHiddenCard(nextCard());
		player3.takeVisibleCard(nextCard());   	
	}

	/**
	 * Returns a random "card", represented by an integer between 1 and 10, inclusive. 
	 * The odds of returning a 10 are four times as likely as any other value (because in an actual
	 * deck of cards, 10, Jack, Queen, and King all count as 10).
	 * 
	 * Note: The java.util package contains a Random class, which is perfect for generating random numbers.
	 * @return a random integer in the range 1 - 10.
	 */
	public int nextCard() {
		// create random numbers
		int card = random.nextInt(13)+1;
		// treat 11, 12 and 13 as 10
		if (card > 10) {
			card = 10;
		}
		return card;    	    	
	}

	/**
	 * Gives each player in turn a chance to take a card, until all players have passed. Prints a message when 
	 * a player passes. Once a player has passed, that player is not given another chance to take a card.
	 * @param scanner to use for user input
	 */
	public void controlPlay(Scanner scanner) { 
		// human	
		// see if the player want to pass
		if (human.passed == false) {
			// offer a new card
			if (human.offerCard(human, player1, player2, player3, scanner) == true) {
				human.takeVisibleCard(nextCard());
			} else {
				// when pass, pass
				System.out.println(human.name + " passed.");
			}
		}
		// computer player1
		// test pass
		if (player1.passed == false) {
			// offer a new card, when not pass
			if ((player1.offerCard(human, player1, player2, player3) == false)) {
				player1.takeVisibleCard(nextCard());
			} else {
				// pass, when the player want to pass
				System.out.println(player1.name + " passed.");
			}
		}	
		// computer player2
		//test pass
		if (player2.passed == false) {
			// offer card
			if ((player2.offerCard(human, player1, player2, player3) == false)) {
				player2.takeVisibleCard(nextCard());
			} else {
				// pass
				System.out.println(player2.name + " passed");
			}
		}
		// computer player3
		// test pass
		if (player3.passed == false) {
			// deal new card
			if ((player3.offerCard(human, player1, player2, player3) == false)) {
				player3.takeVisibleCard(nextCard());
			} else {
				// pass when the player wanna pass
				System.out.println(player3.name + " passed");
			}
		}
	}

	/**
	 * Checks if all players have passed.
	 * @return true if all players have passed
	 */
	public boolean checkAllPlayersHavePassed() {
		// if every player passes, then allPassed
		boolean allPassed = false;
		if ((human.passed == true) && 
				(player1.passed == true) &&
				(player2.passed == true) &&
				(player3.passed == true)) {
			allPassed = true;
		}
		return allPassed;
	}

	/**
	 * Prints a summary at the end of the game.
	 * Displays how many points each player had, and if applicable, who won.
	 */
	public void printResults() { 
		// when everyone passes, the game is over, print scores
		System.out.println("All players passed. Game is over.");
		System.out.println(human.name + " has " + human.getScore() + " total points.");
		System.out.println(player1.name + " has " + player1.getScore() + " total points.");
		System.out.println(player2.name + " has " + player2.getScore() + " total points.");
		System.out.println(player3.name + " has " + player3.getScore() + " total points.");
	}

	/**
	 * Determines who won the game, and prints the results.
	 */
	public void printWinner() { 
		// define a integer to store the smallest difference between players' scores and 21
		int smallestDifference;
		// define a string to store the name for a possible winner
		String winnerCandidate;
		// define a boolean to check whether there is a tie
		boolean tie = false;
		// initialize winner, no winner yet
		winnerCandidate = null;
		// initialize biggest "smallest difference", 21-0=21
		smallestDifference = 21;

		// human check
		// if the score did not go above 21 and
		// if the difference is smaller than 21
		if ((0 <= human.howClose()) && (human.howClose() < smallestDifference)) {
			// if so, assign human's difference to 21 as the smallest
			smallestDifference = human.howClose();
			// human i a possible winner
			winnerCandidate = human.name;
		}

		// player 1 check
		// if the score is not above 21 and
		// if the difference is smaller than the one that's already been set
		if ((0 <= player1.howClose()) && (player1.howClose() < smallestDifference)) {
			// if so, reassign smallest difference
			// and change possible winner
			smallestDifference = player1.howClose();
			winnerCandidate = player1.name;
			// if two plays have same score	
		} else if ((0 < player1.howClose()) && (player1.howClose() == smallestDifference)) {
			// consider it a tie for now
			tie = true;
		}

		// player 2 check
		// as above in player 1 check
		if ((0 <= player2.howClose()) && (player2.howClose() < smallestDifference)) {
			// if player is closer to 21 than previous two players
			// break the tie between those two, if there is one
			tie = false;
			// reassign smallest
			smallestDifference = player2.howClose();
			// update possible winner
			winnerCandidate = player2.name;
			// if player 2's score is similar to any of previous players
			// raise a tie
		}else if ((0 < player2.howClose()) && (player2.howClose() == smallestDifference)) {
			tie = true;
		}

		// player 3 check
		if ((0 <= player3.howClose()) && (player3.howClose() < smallestDifference)) {
			// break previous ties
			tie = false;
			// update closest value
			smallestDifference = player3.howClose();
			// update player 3 as the winner
			winnerCandidate = player3.name;
			// raise a tie, if scores tie
		}else if ((0 < player3.howClose()) && (player3.howClose() == smallestDifference)) {
			tie = true;
		}

		// check the winner
		// if there is a winner, that not everyone's score is above 21
		if (winnerCandidate != null) {
			// check the tie
			// if there is no tie, then the winner is valid
			if (tie != true) {
				// print the winner announcement
				System.out.println("\n" + winnerCandidate + " wins with " + (21-smallestDifference) + "!!!");
			} else {
				// if there is a tie, print tie
				System.out.println("\nTie, nobody wins.");
			}	
			// if there is no possible winner, every player's score is above 21
		} else {
			// print nobody wins
			System.out.println("\nOops, everyone went over 21. Nobody wins.");
		}
	}
}
