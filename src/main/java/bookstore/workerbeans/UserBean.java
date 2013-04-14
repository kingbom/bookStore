package bookstore.workerbeans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.entity.User;

public interface UserBean {
	
	public User getUser(String email, String password);
	public User insertUser(HttpServletRequest request, HttpServletResponse response);
	public User updateUser(HttpServletRequest request, HttpServletResponse response);

}
