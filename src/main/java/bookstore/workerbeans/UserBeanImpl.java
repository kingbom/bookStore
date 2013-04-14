package bookstore.workerbeans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bookstore.Address;
import bookstore.entity.User;
import bookstore.repository.UserRepository;

@Component
public class UserBeanImpl implements UserBean{

	@Autowired
	private UserRepository repository;

	@Override
	public User getUser(String email, String password) {
		return repository.findUserByEmailIgnoreCaseAndPassword(email, password);
	}

	@Override
	public User insertUser(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("userbean");

		User dbUser = repository.save(user);	

		return dbUser;
	}

	@Override
	public User updateUser(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("userbean");

		User dbUser = repository.save(user);	

		return dbUser;
	}

	
}
