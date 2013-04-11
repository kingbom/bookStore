package bookstore;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import stemen.entity.User;
import stemen.repository.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:simple-repository-context.xml")
public class TestUserRepository {
	private final Logger LOGGER = Logger.getLogger(TestUserRepository.class);

	@Autowired
	UserRepository repository;
	private User tom;
	private User patrick;
	private User john;

	@Before
	public void setUp() {
		tom = new User("123 California", "Apt 143", "LA", "Tom@gmail.com", "Tom", "Hanks", "Itsasecret", "CA","54221");
		patrick = new User("847 Mapple Dr.", "", "Washington", "Patrick@gmail.com", "Patrick", "Steward", "moneyMonkey", "MD","64541");
		john = new User("8484 Bristol", "", "Columbus", "john@gmail.com", "John", "Roberts", "pass", "OH","57963");
		repository.save(tom);
		repository.save(patrick);
		repository.save(john);
		repository.flush();
		//assertThat(repository.count(), equalTo(3L));
	}
	
	

	/**
	 * Tests inserting a user and asserts it can be loaded again.
	 */
	@Test
	public void testThatTomCanBeInserted() {
		User retrievedUser = repository.save(tom);
		assertThat(retrievedUser, equalTo(tom));
		assertEquals(tom, repository.findOne(retrievedUser.getId()));
	}
	
	@Test
	public void testThatJohnCanBeFoundByEmailAndPassword(){
		User retreivedUser = repository.findUserByEmailIgnoreCaseAndPassword(john.getEmail(), john.getPassword());
		assertThat(retreivedUser, equalTo(john));
	}

}
