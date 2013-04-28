package bookstore.workerbeans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bookstore.entity.User;
import bookstore.repository.UserRepository;

@Component
public class UserDatabaseAccessor{

	@Autowired
	private UserRepository repository;
	@Transactional
	public User getUser(String email, String password) {
		return repository.findUserByEmailIgnoreCaseAndPassword(email, password);
	}
	@Transactional
	public User getUser(String email) {
		return repository.findUserByEmailIgnoreCase(email);
	}

	@Transactional
	public User insertUser(User user) {
		System.out.println("There are " + repository.count() + " users in the db.");
		System.out.println("Inserting User into db");
		System.out.println("After inserting the user, there are " + repository.count() + " users in the db.");
		User dbUser = repository.save(user);	
		System.out.println("After inserting the user, there are " + repository.count() + " users in the db.");
		return dbUser;
	}
	@Transactional
	public User updateUser(User user) {

		User dbUser = repository.save(user);	

		return dbUser;
	}

	
}
