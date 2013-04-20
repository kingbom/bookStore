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
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import bookstore.entity.User;
import bookstore.repository.UserRepository;
import bookstore.workerbeans.BookDatabaseAccessor;
import bookstore.workerbeans.UserDatabaseAccessor;

/**
 * Servlet implementation class BookStoreServlet
 */
@Component
@WebServlet(description = "Book Store Servlet for 605.782 Class Project", urlPatterns = { "/BookStoreServlet" })
public class BookStoreServlet extends HttpServlet {
	
	public static final String SESSION_USER = "user";
	private static final long serialVersionUID = 1L;
       
	//@Autowired
	UserDatabaseAccessor userDatabaseAccessor;
	BookDatabaseAccessor bookDatabaseAccessor;
	
	ClassPathXmlApplicationContext springContext;
	
    /* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		String contextLocation = "WEB-INF/spring/applicationContext.xml";
		//String contextLocation = "file:src/main/webapp/WEB-INF/spring/applicationContext.xml";
		springContext = new ClassPathXmlApplicationContext(contextLocation);
		userDatabaseAccessor=springContext.getBean(UserDatabaseAccessor.class);
		bookDatabaseAccessor=springContext.getBean(BookDatabaseAccessor.class);
	}
	
	



	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		super.destroy();
		springContext.close();
	}





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
		String command;
		User user;
		String url = "/main.jsp";
		
		command = request.getParameter("command");
		
		System.out.println("Command: "+ command);
		
		if (command != null) {
			if (command.equalsIgnoreCase("CreateUser")) {
				
				user = createUserFromRequest(request);
				user.validateUser();
				if (user.isUserValid()) {
					userDatabaseAccessor.insertUser(user);
					url = "/main.jsp";
				}
				else {
					url = "/user_profile.jsp";
				}
				request.getSession().setAttribute(SESSION_USER, user);
			}
			else if (command.equalsIgnoreCase("UpdateUser")) {
				user = createUserFromRequest(request);
				user.validateUser();
				if (user.isUserValid()) {
					userDatabaseAccessor.updateUser(user);
					url = "/main.jsp";
				}
				else {
					url = "/user_profile.jsp";
				}
				request.getSession().setAttribute(SESSION_USER, user);				
			}
		}
		System.out.println("url: " + url);
		System.out.println(getServletContext().toString());

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
	
	private User createUserFromRequest (HttpServletRequest request) {
		System.out.println("Email: " + request.getParameter("emailAddress"));
		
		User user = (User) request.getSession().getAttribute(SESSION_USER);
		if(user == null) {
			user = new User();
		}
		user.setEmail(request.getParameter("emailAddress"));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setPassword(request.getParameter("password"));
		user.setAddressFirstLine(request.getParameter("addrFirstLine"));
		user.setAddressSecondLine(request.getParameter("addrSecondLine"));
		user.setCity(request.getParameter("addrCity"));
		user.setState(request.getParameter("addrState"));
		user.setZipcode(request.getParameter("addrZip"));

		return user;
	}

}
