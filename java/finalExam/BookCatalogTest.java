package book;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookCatalogTest {


	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	void testBookCatalog() {

		BookCatalog bookCatalog = new BookCatalog();

		//confirm book map is empty
		assertEquals(0, bookCatalog.getBookMap().size());
	}

	@Test
	void testAddBook() {
		// TODO Write test cases
		BookCatalog bookCatalog = new BookCatalog();

		List<String> lines = new ArrayList<String>();
		String title = "Title: Zoe";
		lines.add(title);
		String author = "Author: Zoe";
		lines.add(author);
		String content = "Yeah";
		lines.add(content);
		Book book = new Book(lines);
		bookCatalog.addBook(book);
		assertEquals(1, bookCatalog.getBookMap().size());


		List<String> lines2 = new ArrayList<String>();
		String title2 = "Title: Java";
		lines2.add(title2);
		String author2 = "Author: Python Lover";
		lines2.add(author2);
		String content2 = "Happy winter break";
		lines2.add(content2);
		Book book2 = new Book(lines2);
		bookCatalog.addBook(book2);
		assertEquals(2, bookCatalog.getBookMap().size());

	}

	@Test
	void testGetBookByTitle() {
		// TODO Write test cases
		BookCatalog bookCatalog = new BookCatalog();

		List<String> lines = new ArrayList<String>();
		String title = "Title: Zoe";
		lines.add(title);
		String author = "Author: Zoe";
		lines.add(author);
		String content = "Yeah";
		lines.add(content);
		Book book = new Book(lines);
		bookCatalog.addBook(book);		

		List<String> lines2 = new ArrayList<String>();
		String title2 = "Title: Java";
		lines2.add(title2);
		String author2 = "Author: Python Lover";
		lines2.add(author2);
		String content2 = "Happy winter break";
		lines2.add(content2);
		Book book2 = new Book(lines2);
		bookCatalog.addBook(book2);

		assertEquals(book, bookCatalog.getBookByTitle("Zoe"));
		assertEquals(book2, bookCatalog.getBookByTitle("Java"));
	}

	@Test
	void testGetBookByAuthor() {
		// TODO Write test cases
		BookCatalog bookCatalog = new BookCatalog();

		List<String> lines = new ArrayList<String>();
		String title = "Title: Zoe";
		lines.add(title);
		String author = "Author: Zoe";
		lines.add(author);
		String content = "Yeah";
		lines.add(content);
		Book book = new Book(lines);
		bookCatalog.addBook(book);		

		List<String> lines2 = new ArrayList<String>();
		String title2 = "Title: Java";
		lines2.add(title2);
		String author2 = "Author: Python Lover";
		lines2.add(author2);
		String content2 = "Happy winter break";
		lines2.add(content2);
		Book book2 = new Book(lines2);
		bookCatalog.addBook(book2);

		assertEquals(book, bookCatalog.getBookByAuthor("Zoe"));
		assertEquals(book2, bookCatalog.getBookByAuthor("Python Lover"));
	}

}
