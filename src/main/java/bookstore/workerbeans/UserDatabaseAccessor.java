package bookstore.workerbeans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bookstore.entity.User;
import bookstore.repository.UserRepository;

@Component
public class UserDatabaseAccessor{

	@Autowired
	private UserRepository repository;

	public User getUser(String email, String password) {
		return repository.findUserByEmailIgnoreCaseAndPassword(email, password);
	}

	public User insertUser(User user) {

		User dbUser = repository.save(user);	

		return dbUser;
	}

	public User updateUser(User user) {

		User dbUser = repository.save(user);	

		return dbUser;
	}

	
}
