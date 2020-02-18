package hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clean the file content and obtain a list of legal words.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
class BuildDictionary {

	/**
	 * Open and read data in filename.
	 * Clean the data line by line, 
	 * remove the whitespace at the beginning and end of each line.
	 * Return a list of words.
	 * @param filename of the file to clean
	 * @return a list of legal words
	 */
	public static ArrayList<String> getWords(String filename) {
		//creates new instance of String ArrayList
		ArrayList<String> words = new ArrayList<String>();

		//creates new BufferedReader
		BufferedReader file = null;

		//opens the file with given name
		//reads the file line by line and trim whitespace
		//adds the clean content to a list
		try {
			file = new BufferedReader(new FileReader(filename));

			String line = file.readLine();

			while (line != null) {
				line = line.trim();

				if (!line.isEmpty()) {
					words.add(line);
				}

				line = file.readLine();
			}
			//catch fileNotFoundException
		} catch (FileNotFoundException e){
			e.printStackTrace();
			
			//catch IOException
		} catch (IOException e) {
			e.printStackTrace();

			//try to close the file
		} finally {
			try {
				file.close();
				
				//catch IOException
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//return the words ArrayList
		return words;
	}

	/**
	 * Removes words containing digits, abbreviations,
	 * apostrophes, hyphens, whitespace or upper case letters.
	 * @param list to clean up
	 * @return updated list
	 */
	public static ArrayList<String> cleanWords(ArrayList<String> list) {
		//creates an iterator
		Iterator<String> word = list.iterator();

		//creates new regex String
		String regex = "[0-9-.'\\s]+";
		
		//checks if the word contains upper case letter or special symbols
		//removes illegal words
		Pattern p = Pattern.compile(regex);
		while(word.hasNext()) {
			try {
				String wordTest = word.next();
				Matcher m = p.matcher(wordTest);
				if (!wordTest.equals(wordTest.toLowerCase()) || m.find()) {
					word.remove();
				}
				//catches NoSuchElementException
			}catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		//returns the updated list 
		return list;
	}
}

