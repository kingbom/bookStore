package bookstore;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import bookstore.entity.User;
import bookstore.workerbeans.UserDatabaseAccessor;

/**
 * Servlet implementation class BookStoreServlet
 */
@WebServlet(description = "Book Store Servlet for 605.782 Class Project", urlPatterns = { "/BookStoreServlet" })
public class BookStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	UserDatabaseAccessor userDatabaseAccessor;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookStoreServlet() {
        super();
    }



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command;
		User userBean;
		String url = "/main.jsp";
		
		command = request.getParameter("command");
		
		System.out.println("Command: "+ command);
		
		if (command != null) {
			if (command.equalsIgnoreCase("CreateUser")) {
				
				userBean = updateUser(request, response);
				userBean.validateUser();
				if (userBean.isUserValid()) {
					userDatabaseAccessor.insertUser(userBean);
					url = "/main.jsp";
				}
				else {
					url = "/user_profile.jsp";
				}
				request.getSession().setAttribute("userbean", userBean);
			}
			else if (command.equalsIgnoreCase("UpdateUser")) {
				userBean = updateUser(request, response);
				userBean.validateUser();
				if (userBean.isUserValid()) {
					userDatabaseAccessor.updateUser(userBean);
					url = "/main.jsp";
				}
				else {
					url = "/user_profile.jsp";
				}
				request.getSession().setAttribute("userbean", userBean);				
			}
		}
		System.out.println("url: " + url);
		System.out.println(getServletContext().toString());

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
	
	private User updateUser (HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Email: " + request.getParameter("emailAddress"));
		
		User userBean = (User) request.getSession().getAttribute("userbean");
		if(userBean == null) {
			userBean = new User();
		}
		userBean.setEmail(request.getParameter("emailAddress"));
		userBean.setFirstName(request.getParameter("firstName"));
		userBean.setLastName(request.getParameter("lastName"));
		userBean.setPassword(request.getParameter("password"));
		userBean.setAddressFirstLine(request.getParameter("addrFirstLine"));
		userBean.setAddressSecondLine(request.getParameter("addrSecondLine"));
		userBean.setCity(request.getParameter("addrCity"));
		userBean.setState(request.getParameter("addrState"));
		userBean.setZipcode(request.getParameter("addrZip"));

		return userBean;
	}

}
