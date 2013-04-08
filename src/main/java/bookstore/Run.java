package bookstore;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stemen.entity.User;
import stemen.repository.UserRepository;


@ContextConfiguration(locations = "classpath:simple-repository-context.xml")
public class Run {
	@Autowired
	UserRepository repository;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User tom = new User("123 California", "Apt 143", "LA", "Tom@gmail.com", "Tom", "Hanks", "Itsasecret", "CA","54221");
		User patrick = new User("847 Mapple Dr.", "", "Washington", "Patrick@gmail.com", "Patrick", "Steward", "moneyMonkey", "MD","64541");
		User  john = new User("8484 Bristol", "", "Columbus", "john@gmail.com", "John", "Roberts", "pass", "OH","57963");
		repository.save(tom);
		repository.save(patrick);
		repository.save(john);
	}

}
