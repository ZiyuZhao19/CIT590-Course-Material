package hangman;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for superclass Hangman and HangmanTraditional and HangmanEvil subclass.
 * @author Xiang Zhou & Ziyu Zhao
 * PennKey: xzhoukkk & zzhao19
 */
class HangmanTest {

	HangmanTraditional traditional;
	HangmanEvil evil;
	
	/**
	 * @throws java.lang.Exception exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("apple"));
		this.traditional = new HangmanTraditional(list);
		this.evil = new HangmanEvil(list);
		
	}

	@Test
	void testHangman() {
		
		ArrayList<String> list2 = new ArrayList<String>(Arrays.asList("apple"));
		assertEquals(0, traditional.getIndex());
		assertEquals("apple", traditional.getWord());
		assertTrue(list2.equals(traditional.getSameLengthGroup()));
		
		assertEquals(0, evil.getIndex());
		assertEquals("apple", evil.getWord());
		assertTrue(list2.equals(evil.getSameLengthGroup()));
		
	}

	@Test
	void testLetterGuessed() {
		
		assertFalse(traditional.letterGuessed('b'));
		assertTrue(traditional.letterGuessed('a'));
		assertTrue(traditional.getWordGuessed()[0]);
		assertFalse(traditional.getWordGuessed()[1]);
		assertTrue(traditional.letterGuessed('p'));
		assertTrue(traditional.getWordGuessed()[1]);
		assertTrue(traditional.getWordGuessed()[2]);
		assertFalse(traditional.letterGuessed('a'));
		
		assertFalse(evil.letterGuessed('b'));
		assertTrue(evil.letterGuessed('a'));
		assertTrue(evil.getWordGuessed()[0]);
		assertFalse(evil.getWordGuessed()[1]);
		assertTrue(evil.letterGuessed('p'));
		assertTrue(evil.getWordGuessed()[1]);
		assertTrue(evil.getWordGuessed()[2]);
		assertFalse(evil.letterGuessed('a'));
		
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("apple", "watch", "water", "clock", "faker", "batch"));
		this.evil = new HangmanEvil(list);
		assertTrue(evil.letterGuessed('a'));
		assertTrue(evil.getWordGuessed()[1]);
		assertFalse(evil.letterGuessed('o'));
		assertFalse(evil.letterGuessed('k'));
		assertTrue(evil.letterGuessed('t'));
		assertTrue(evil.getWordGuessed()[2]);
		assertFalse(evil.letterGuessed('b'));
		assertTrue(evil.letterGuessed('w'));
		assertTrue(evil.getWordGuessed()[0]);
		
	}
	
	@Test
	void testGetGameMode() {
		
		assertEquals(traditional.getGameMode(), "traditional");
		assertEquals(evil.getGameMode(), "evil");
		
	}

	@Test
	void testIsGameOver() {
		
		assertFalse(traditional.isGameOver());
		traditional.letterGuessed('a');
		assertFalse(traditional.isGameOver());
		traditional.letterGuessed('p');
		traditional.letterGuessed('l');
		traditional.letterGuessed('e');
		assertTrue(traditional.isGameOver());
		
		assertFalse(evil.isGameOver());
		evil.letterGuessed('a');
		assertFalse(evil.isGameOver());
		evil.letterGuessed('p');
		evil.letterGuessed('l');
		evil.letterGuessed('e');
		assertTrue(evil.isGameOver());
		
	}

}
