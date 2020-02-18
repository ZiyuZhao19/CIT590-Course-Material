package book;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {
	
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testBook() {
		List<String> bookLines = new ArrayList<String>();
		Book book = new Book(bookLines);
		
		//confirm book lines is empty
		assertEquals(0, book.getLines().size());
	}

	@Test
	void testGetTitle() {
		// TODO Write test cases
		List<String> lines = new ArrayList<String>();
		String title = "Title: Zoe's Awesome";
		lines.add(title);
		String author = "Author: Zoe";
		lines.add(author);
		String content = "I love CIT 590. Many thanks to Brandon and all the TAs.";
		lines.add(content);
		Book book = new Book(lines);
		assertEquals("Zoe's Awesome", book.getTitle());
		
		List<String> lines2 = new ArrayList<String>();
		String title2 = "Title: Java's so fun";
		lines2.add(title2);
		String author2 = "Author: Python Lover";
		lines2.add(author2);
		String content2 = "Happy winter break.";
		lines2.add(content2);
		Book book2 = new Book(lines2);
		assertEquals("Java's so fun", book2.getTitle());
	}

	@Test
	void testGetAuthor() {
		// TODO Write test cases
		List<String> lines = new ArrayList<String>();
		String title = "Title: Zoe's Awesome";
		lines.add(title);
		String author = "Author: Zoe";
		lines.add(author);
		String content = "I love CIT 590. Many thanks to Brandon and all the TAs.";
		lines.add(content);
		Book book = new Book(lines);
		assertEquals("Zoe", book.getAuthor());
		
		List<String> lines2 = new ArrayList<String>();
		String title2 = "Title: Java's so fun";
		lines2.add(title2);
		String author2 = "Author: Python Lover";
		lines2.add(author2);
		String content2 = "Happy winter break.";
		lines2.add(content2);
		Book book2 = new Book(lines2);
		assertEquals("Python Lover", book2.getAuthor());
	}

	@Test
	void testGetTotalWordCount() {
		// TODO Write test cases
		List<String> lines = new ArrayList<String>();
		String title = "Title: Zoe";
		lines.add(title);
		String author = "Author: Brandon";
		lines.add(author);
		String content = "Yeah";
		lines.add(content);
		Book book = new Book(lines);
		assertEquals(5, book.getTotalWordCount());
		
		List<String> lines2 = new ArrayList<String>();
		String title2 = "Title: Java";
		lines2.add(title2);
		String author2 = "Author: Python Lover";
		lines2.add(author2);
		String content2 = "Happy winter break";
		lines2.add(content2);
		Book book2 = new Book(lines2);
		assertEquals(8, book2.getTotalWordCount());
	}

	@Test
	void testGetUniqueWordCount() {
		// TODO Write test cases
		List<String> lines = new ArrayList<String>();
		String title = "Title: Zoe";
		lines.add(title);
		String author = "Author: Zoe";
		lines.add(author);
		String content = "Yeah";
		lines.add(content);
		Book book = new Book(lines);
		assertEquals(4, book.getUniqueWordCount());
		
		List<String> lines2 = new ArrayList<String>();
		String title2 = "Title: Java";
		lines2.add(title2);
		String author2 = "Author: Python Lover";
		lines2.add(author2);
		String content2 = "Happy winter break";
		lines2.add(content2);
		Book book2 = new Book(lines2);
		assertEquals(8, book2.getUniqueWordCount());
	}

	@Test
	void testGetSpecificWordCount() {
		// TODO Write test cases
		List<String> lines = new ArrayList<String>();
		String title = "Title: Zoe";
		lines.add(title);
		String author = "Author: Zoe";
		lines.add(author);
		String content = "Yeah";
		lines.add(content);
		Book book = new Book(lines);
		assertEquals(2, book.getSpecificWordCount("Zoe"));
		
		List<String> lines2 = new ArrayList<String>();
		String title2 = "Title: Java";
		lines2.add(title2);
		String author2 = "Author: Python Lover";
		lines2.add(author2);
		String content2 = "Happy winter break";
		lines2.add(content2);
		Book book2 = new Book(lines2);
		assertEquals(1, book2.getSpecificWordCount("Java"));
	}

	//EXTRA CREDIT!
	@Test
	void testGetFirstLines() {
		// TODO Write test cases
	}

	//EXTRA CREDIT!
	@Test
	void testGetFirstLinesInt() {
		// TODO Write test cases
	}

}
