package bookstore.workerbeans;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bookstore.entity.Book;
import bookstore.entity.Book.Category;
import bookstore.repository.BookRepository;
import bookstore.workerbeans.BookDatabaseAccessor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
public class TestBookDatabaseAccessor {

	@Autowired
	private BookDatabaseAccessor bookDatabaseAccessor;

	Book bookToSave;
	String title = "The Best of Me";
	
	@Before
	public void setUp() throws Exception {
		
		bookToSave = new Book (title, "Nicholas Sparks", 14.99, 3.8,"00004", 104, Category.Romance,
				"A high school couple, separated 25 years later, rekindle their love after a friend’s funeral.", true, "00004.jpeg");
		bookDatabaseAccessor.insertBook(bookToSave);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testThatGetBookByKeywordFindsPartialTitles() {
		List<Book> books = bookDatabaseAccessor.getBookByKeyword("Best");
		assertThat(books, hasItem(bookToSave));
	}

}
