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
import bookstore.entity.User;
import bookstore.entity.Book.Category;
import bookstore.repository.BookRepository;
import bookstore.workerbeans.BookDatabaseAccessor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
public class TestUserDatabaseAccessor {

	@Autowired
	private UserDatabaseAccessor userDatabaseAccessor;

	private User tom;
	private User patrick;
	private User john;

	@Before
	public void setUp() {
		tom = new User("123 California", "Apt 143", "LA", "Tom@gmail.com", "Tommy", "Hanks", "Itsasecret345", "CA","54221");
		patrick = new User("847 Mapple Dr.", "", "Washington", "Patrick@gmail.com", "Patrick", "Steward345345", "moneyMonkey", "MD","64541");
		john = new User("8484 Bristol", "", "Columbus", "john@gmail.com", "John", "Roberts", "pass3453453", "OH","57963");

		userDatabaseAccessor.insertUser(tom);
		userDatabaseAccessor.insertUser(patrick);
		userDatabaseAccessor.insertUser(john);
		//assertThat(repository.count(), equalTo(3L));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testgetUserByEmail() {
		User foundUser = userDatabaseAccessor.getUser("Tom@gmail.com".toUpperCase());
		assertThat(foundUser, equalTo(tom));
	}

}
