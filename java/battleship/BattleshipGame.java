package battleship;

import java.util.Scanner;

/**
 * Main class for a Human vs Computer version of Battleship.
 * Creates a single instance of Ocean. Get user input (row and column)
 * for interacting with and playing against the Computer.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
class BattleshipGame {

	/**
	 * Print instruction for users
	 */
	private static void printInstructions() {

		System.out.println("Welcome to Battleship! "
				+ "\nIn this game, you fire at ships in a 10X10 ocean with unknown coordinates. "
				+ "\nFor each shot, enter a pair of 0-9 comma separated integers."
				+ "\nYou'll see a . representing the location is never fired before."
				+ "\nx indicates only part of the ship is hit but the ship is not sunk yet."
				+ "\ns replies the ship at that location is successfully sunk."
				+ "\nTry as few times as you can to get all the ships sunk!"
				+ "\nEnjoy!");
		System.out.println();
	}

	/**
	 * Main method
	 * Set up the game
	 * Take user inputs
	 * Display the results and print final scores
	 * Ask the user if he/she wants to play again 
	 * @param args
	 */
	public static void main(String[] args) {

		//set up the boolean variable to control play again
		boolean playAgain = true;

		//initialize the scanner
		Scanner scanner = new Scanner(System.in);

		//create the while loop to keep asking the users' intention
		//to play again, break if the user responds no
		while (playAgain == true) {

			//print instructions
			BattleshipGame.printInstructions();

			//create an empty ocean
			Ocean ocean = new Ocean();

			//place 10 ships in the ocean
			ocean.placeAllShipsRandomly();

			//print the initialized ocean
			ocean.print();

			//create two integer variables to store user input for row and column
			int row, column;

			//create a string variable to read input
			String target;

			//create a string array to store integer inputs
			String[] targetLoc;

			//while the conditions for ending games are not met
			//keep asking for user moves
			while (ocean.isGameOver() != true) {

				//initialize "illegal" row &column to be replaced by "legal" inputs
				row = 10;
				column = 10;

				//get user input
				//prompt for another user input if the answer is not valid
				while ((row < 0) || (row > 9) || (column < 0) || (column > 9)) {

					//try to match user input with int,int format
					try {

						//read input line and integer inputs
						target = scanner.nextLine();
						targetLoc = target.split(",");

						//assign input value to row & column
						row = Integer.parseInt(targetLoc[0].trim());
						column = Integer.parseInt(targetLoc[1].trim());

						//if the inputs are out of bounds, ask for inputs again
						if ((row < 0) || (row > 9) || (column < 0) || (column > 9)) {
							System.out.println("Please enter integers from 0 to 9");
						}

						//if user inputs cause an out of bounds exception, ask again
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Please enter a pair of integers");
						row = 10;
						column = 10;

						//if user inputs are not in the expected format, ask again
					} catch (NumberFormatException e){
						System.out.println("Please enter a pair of integers");
						row = 10;
						column = 10;
					}
				}

				//confirm user inputs
				System.out.print("You chose row " + row + ", column " + column + "\n");

				//gets ship information with given location
				Ship ship = ocean.getShipArray()[row][column];

				//checks if a ship is hit
				//if yes updates the ship condition
				boolean result = ship.shootAt(row,column);

				//checks if the location contains a real ship
				//and updates the times of shots
				ocean.shootAt(row, column);

				//prints information about a sunk ship
				if ((result == true) && (ship.isSunk() == true)) {
					System.out.println("You just sank a ship: " + ship.getShipType()+ "\n");
				}

				//prints number of shots fired and hit times
				System.out.println("Your total number of shots: " + ocean.getShotsFired());
				System.out.println("Your total number of times a shot hit a ship: " + ocean.getHitCount());
				System.out.println();

				//print ocean after user's shot
				ocean.print();

				//check if all ships are sunk
				ocean.isGameOver();
			}

			//print final results
			System.out.println("The game is over.");
			System.out.println("Your total number of shots: " + ocean.getShotsFired());
			System.out.println("Your total number of times a shot hit a ship: " + ocean.getHitCount());

			//ask about user's intention for another round
			System.out.println("Wanna play again (y/n)");

			//read user's input
			String answer = scanner.nextLine();

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

		//close the scanner
		scanner.close();
	}

}
