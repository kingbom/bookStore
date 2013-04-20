package bookstore;

import java.io.IOException;
import java.util.List;

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

import bookstore.entity.Book;
import bookstore.entity.Book.Category;
import bookstore.entity.User;
import bookstore.workerbeans.BookDatabaseAccessor;
import bookstore.repository.UserRepository;
import bookstore.workerbeans.BookDatabaseAccessor;
import bookstore.workerbeans.UserDatabaseAccessor;

/**
 * Servlet implementation class BookStoreServlet
 */
@Component
@WebServlet(description = "Book Store Servlet for 605.782 Class Project", urlPatterns = { "/BookStoreServlet" })
public class BookStoreServlet extends HttpServlet {
	
	private static final String ON_SPECIAL_CMD = "OnSpecial";
	private static final String SEARCH_CMD = "search";
	private static final String CATEGORY_CMD = "category";
	private static final String USER_PROFILE_JSP = "/user_profile.jsp";
	private static final String MAIN_JSP = "/main.jsp";

	private static final String DISPLAY_MAIN_PAGE_CMD = "DisplayMainPage";
	private static final String UPDATE_USER_CMD = "UpdateUser";
	private static final String CREATE_USER_CMD = "CreateUser";
	private static final String COMMAND = "command";
	
	public static final String SESSION_USER = "user";
	public static final String SESSION_LIST = "booklist";
	
	private static final long serialVersionUID = 1L;
       
	//@Autowired
	UserDatabaseAccessor userDatabaseAccessor;
	//@Autowired
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
		String url = MAIN_JSP;
		
		command = request.getParameter(COMMAND);
		
		System.out.println("Command: "+ command);
		
		if (command != null) {
			if (command.equalsIgnoreCase(CREATE_USER_CMD)) {
				
				user = createUserFromRequest(request);
				user.validateUser();
				if (user.isUserValid()) {
					userDatabaseAccessor.insertUser(user);
					url = MAIN_JSP;
				}
				else {
					url = USER_PROFILE_JSP;
				}
				request.getSession().setAttribute(SESSION_USER, user);
			}
			else if (command.equalsIgnoreCase(UPDATE_USER_CMD)) {
				user = createUserFromRequest(request);
				user.validateUser();
				if (user.isUserValid()) {
					userDatabaseAccessor.updateUser(user);
					@SuppressWarnings("unchecked")
					List<Book> list = (List<Book>) request.getSession().getAttribute(SESSION_LIST);
					if (list == null) {
						list = bookDatabaseAccessor.getSpecialBooks();
						request.getSession().setAttribute(SESSION_LIST, list);														
					}
					url = MAIN_JSP;
				}
				else {
					url = USER_PROFILE_JSP;
				}
				request.getSession().setAttribute(SESSION_USER, user);				
			}
			else if (command.equalsIgnoreCase(DISPLAY_MAIN_PAGE_CMD)) {
				List<Book> list = getBooksFromRequest (request);
				url = MAIN_JSP;
				request.getSession().setAttribute(SESSION_LIST, list);								
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
	
	private List<Book> getBooksFromRequest (HttpServletRequest request) {
		List<Book> list = null;
		String categoryString;
		String searchString;
		Category category;
		
		categoryString = request.getParameter(CATEGORY_CMD);
		searchString = request.getParameter(SEARCH_CMD);
		
		if (categoryString != null && !categoryString.isEmpty()) {
			if (categoryString.equalsIgnoreCase(ON_SPECIAL_CMD)) {
				list = bookDatabaseAccessor.getSpecialBooks();
			}
			
			else {
				category = Category.valueOf(categoryString);
				list = bookDatabaseAccessor.getBooks(category);				
			}
		}
		
		else if (searchString != null && !searchString.isEmpty()) {
			list = bookDatabaseAccessor.getBookByKeyword(searchString);
		}
		return list;
	}

}
