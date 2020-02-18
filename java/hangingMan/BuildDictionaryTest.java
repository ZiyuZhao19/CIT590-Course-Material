package hangman;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;


/**
 * Test class for BuildDictionary.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
class BuildDictionaryTest {

	ArrayList<String> list;

	@Test
	void testCleanWords() {
		list = new ArrayList<String> (Arrays.asList("apple", "baNana", "1st", "3-D", "compound word", "you're"));
		ArrayList<String> list2 = new ArrayList<String> (Arrays.asList("apple"));
		assertTrue(list2.equals(BuildDictionary.cleanWords(list)));
		list2 = new ArrayList<String> (Arrays.asList("apple", "baNana"));
		assertFalse(list2.equals(BuildDictionary.cleanWords(list)));
		list2 = new ArrayList<String> (Arrays.asList("apple", "1st"));
		assertFalse(list2.equals(BuildDictionary.cleanWords(list)));
	}

}
