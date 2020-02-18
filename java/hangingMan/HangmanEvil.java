package hangman;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * Represents a subclass of Hangman, a case of evil hangman.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
class HangmanEvil extends Hangman{

	/**
	 * set the mode of the game to evil
	 */
	private static final String MODE = "evil";

	/**
	 * create an empty ArrayList to store correct guesses 
	 */
	private List<Character> rightGuesses = new ArrayList<Character>();
	
	/**
	 * call the getWord() function in the super class, to store the first word for the case
	 */
	String firstWord = super.getWord();

	/**
	 * call the getSameLengthGroup() function in the super class
	 */
	ArrayList<String> sameLengthGroup = super.getSameLengthGroup();

	/**
	 * call the constructor in the super class
	 * @param list Take an ArrayList of words as parameter
	 */
	public HangmanEvil(ArrayList<String> list) {
		super(list);
	}

	/**
	 * identify the pattern families of words given one guess
	 * for example, given a guess 'a'
	 * read the words "apple, watch, clock", as "a----, -a---, -----"
	 * to decide which pattern appears most frequently
	 * @param letter Player's input of her guess of letter
	 * @param sameLengthGroup An ArrayList of words of same length of the first word
	 * @param rightGuesses An ArrayList storing guesses that lead to the largest pattern family
	 * @return a list of word family patterns
	 */
	private static ArrayList<String> identifyPattern(char letter, ArrayList<String> sameLengthGroup, List<Character> rightGuesses){
		
		//create a new ArrayList to store every word in sameLengthGroup's abstract pattern
		ArrayList<String> lengthPatterns = new ArrayList<String>();
		
		//iterate over the sameLengthGroup word list
		for (String word: sameLengthGroup) {
			
			//create a new list to store processed letters in the word
			List<String> letters = new ArrayList<String>();

			//iterate every character in the word
			//if the character is not equal to the given guess, store the character as "-"
			//else if the character matches the given guess, store the character as it is
			//add the string of characters to the List letters
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) != letter) {
					if (Character.toString(word.charAt(i)) != "-") {
						letters.add("-");
					}
				}else {
					letters.add(Character.toString(word.charAt(i)));
				}
			}

			//under the condition that there are previous right guesses
			//iterate over the word's characters
			//if any character matches the previous guessed ones
			//store the character as it is rather than "-"
			if (rightGuesses.size() != 0) {
				for (char c: rightGuesses) {
					for (int i = 0; i < word.length(); i++) {
						if (word.charAt(i) == c) {
							letters.set(i,Character.toString(c));
						}
					}
				}
			}
			
			//join the character strings in letters together
			//to form a string pattern
			String wordPattern = String.join("", letters);
			
			//add each word's pattern to the lengthPatterns list
			lengthPatterns.add(wordPattern);
		}
		return lengthPatterns;
	}

	/**
	 * create a map to store each word family and their counts
	 * @param lengthPatterns given pattern to search
	 * @return a map containing word families and their members count
	 */
	private static Map<String, Integer> patternCount(ArrayList<String> lengthPatterns){
		
		//create an empty map with string keys and integer values
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//iterate over the lengthPatterns
		//for each pattern, if not already in the map, add it as a key and set its value to be 1
		//if the pattern is in the map already, increments its count by 1
		for (String pattern: lengthPatterns) {
			if (!map.containsKey(pattern)) {
				map.put(pattern,1);
			}else {
				map.replace(pattern, map.get(pattern)+1);
			}
		}
		return map;
	}

	/**
	 * find the word family with most word members
	 * i.e. the pattern that has greatest count as value in the map
	 * @param patternCountMap the map containing  patterns and their counts
	 * @return return the pattern of the largest word family
	 */
	private String highFrequencyPattern(Map<String, Integer> patternCountMap) {

		//an integer variable to store the max value
		int maxValue = 0;
		
		//a string variable to store the pattern with highest frequency
		String highFreq = "";

		//iterate over the map's values and find the biggest value
		for (Integer value : patternCountMap.values()) {
			if (value > maxValue) {
				maxValue = value;
			}
		}

		//iterate over entries of the map and find the key to the maxValue 
		for(Map.Entry<String, Integer> entry:patternCountMap.entrySet()){
			if(maxValue == (entry.getValue())) {
				highFreq = entry.getKey();
			}
		}
		return highFreq;
	}

	/**
	 * update the sameLengthGroup after a round of guess to a smaller scale
	 * storing only words from the biggest word family
	 * @param lengthPatterns a list containing all abstract patterns of candidate answers
	 * @param pattern The pattern that words from the biggest word family shares
	 */
	private void narrowChoices(ArrayList<String> lengthPatterns, String pattern){
		
		//create an ArrayList to store updated list of words from the biggest word family
		ArrayList<String> updateSameLength = new ArrayList<String>();
		
		//iterate over the lengthPatterns and get the index of patterns equal to the given one
		//and use the index to retrieve the exact concrete word from the original sameLengthGroup list
		//and add the word to the updated list
		for (int i = 0; i < lengthPatterns.size(); i++) {
			if (lengthPatterns.get(i).equals(pattern)) {
				updateSameLength.add(this.sameLengthGroup.get(i));
			}
		}
		
		//update the sameLengthGroup to the updated list
		//to narrow its scale down after each guess
		this.sameLengthGroup = updateSameLength;
	}

	/**
	 * implement the abstract letterGuessed method in the super class
	 * for each round of player guesses, this method does the following
	 * 1. identify the pattern of each word in the given list with words of same length of the first random word
	 * 2. keep each pattern and their counts in a map
	 * 3. find the pattern of the biggest word family
	 * 4. narrow down the sameLengthGroup that stores answer candidates
	 * 5. update wordGuessed boolean list with whether the player makes the guess that generates the biggest word family
	 * 6. return true if the round of guess is successful
	 *    return false if not
	 */
	boolean letterGuessed(char letter) {

		//step 1: use identifyPattern method to create list of patterns
		ArrayList<String> lengthPatterns = identifyPattern(letter, this.sameLengthGroup, rightGuesses);
		//System.out.println(lengthPatterns);

		//step 2: use patternCount method to create a map store patterns and counts
		Map<String, Integer> patternCountMap = patternCount(lengthPatterns);
		//System.out.println(patternCountMap);

		//step 3: get the pattern that most words share
		String pattern = highFrequencyPattern(patternCountMap);
		//System.out.println(pattern);

		//step 4: narrow the sameLengthGroup that stores potential answers
		narrowChoices(lengthPatterns, pattern);
		//System.out.println(this.sameLengthGroup);

		//initialize a boolean variable to see whether the round of guess is successful or not
		boolean guessed = false;

		//update the first word to be the pattern of largest word family
		//rather than the first random word that used to determine the length of candidate words
		//and update each time as the pattern gets concrete with more actual letters and less abstract with "-"
		this.firstWord = pattern;

		//iterate over the booleans in the wordGuessed list
		//under the condition that the character has never been guessed "correctly"
		//if the player's guess matches with character(s) in the pattern
		//consider the guess a success, set every boolean of character that matches and return true
		//else return false
		for (int i = 0; i < this.firstWord.length();i++) {
			if (this.getWordGuessed()[i] == false) {
				if ((pattern.charAt(i) == letter)){
					guessed = true;
					rightGuesses.add(letter);
					this.getWordGuessed()[i] = true;

				}else {
					this.getWordGuessed()[i] = false;
				}
			}
		}
		return guessed;
	}

	/**
	 * an override method to return the mode of the game
	 */
	@Override
	public String getGameMode() {
		return HangmanEvil.MODE;
	}

	/**
	 * due to that the firstWord was updated in this subclass rather than in the super class
	 * override the method that prints the result after each guess
	 */
	@Override
	void print() {
		//System.out.println(this.firstWord);
		
		//create a list of strings to store each character in the word
		List<String> letters = new ArrayList<String>();

		//iterate over the word with all the characters
		//if the character's never been guessed, mark it with "-"
		//else, show the character as it is
		for (int i = 0; i < this.firstWord.length(); i++) {
			if (this.getWordGuessed()[i] == false) {
				if(Character.toString(this.firstWord.charAt(i)) != "-") {
					letters.add("_");
				}
			}else {
				letters.add(Character.toString(this.firstWord.charAt(i)));
			}
		}
		System.out.println(String.join(" ", letters));
	}
}
