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
@WebServlet(description = "Book Store Servlet for 605.782 Class Project", urlPatterns = { "/BookStoreServlet","/bookstoreservlet","/bookStoreServlet" })
public class BookStoreServlet extends HttpServlet {
	
	private static final String QUANTITY_CMD = "quantity";
	private static final String UPDATE_CART_QUANTITY_CMD = "UpdateCartQuantity";
	private static final String REMOVE_FROM_CART_CMD = "RemoveFromCart";
	private static final String ADD_TO_CART_CMD = "AddToCart";
	private static final String ISBN_CMD = "ISBN";
	private static final String ON_SPECIAL_CMD = "OnSpecial";
	private static final String SEARCH_CMD = "search";
	private static final String CATEGORY_CMD = "category";
	
	private static final String DISPLAY_MAIN_PAGE_CMD = "DisplayMainPage";
	private static final String UPDATE_USER_CMD = "UpdateUser";
	private static final String CREATE_USER_CMD = "CreateUser";
	private static final String COMMAND = "command";
	
	public static final String SESSION_USER = "user";
	public static final String SESSION_LIST = "booklist";
	private static final String SESSION_CART = "cart";

	private static final String USER_PROFILE_JSP = "/user_profile.jsp";
	private static final String MAIN_JSP = "/main.jsp";
	private static final String DISPLAY_CART_JSP = "/display_cart.jsp";
	
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
					createBookList(request);
					url = MAIN_JSP;
				}
				
				else {
					url = USER_PROFILE_JSP;
				}
			}
			
			else if (command.equalsIgnoreCase(UPDATE_USER_CMD)) {
				user = createUserFromRequest(request);
				user.validateUser();
				if (user.isUserValid()) {
					userDatabaseAccessor.updateUser(user);
					createBookList(request);
					url = MAIN_JSP;
				}
				else {
					url = USER_PROFILE_JSP;
				}
			}
			
			else if (command.equalsIgnoreCase(DISPLAY_MAIN_PAGE_CMD)) {
				getBooksFromRequest (request);
				url = MAIN_JSP;
			}
			else if (command.equalsIgnoreCase(ADD_TO_CART_CMD) ||
					command.equalsIgnoreCase(REMOVE_FROM_CART_CMD) ||
					command.equalsIgnoreCase(UPDATE_CART_QUANTITY_CMD)) {
				updateCartFromRequest(request, command);
				url = DISPLAY_CART_JSP;
			}			
		}
		System.out.println("url: " + url);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

	private void updateCartFromRequest(HttpServletRequest request, String command) {
		
		String isbn = null;

		isbn = request.getParameter(ISBN_CMD);

		if (isbn != null && !isbn.isEmpty()) {
			@SuppressWarnings("unchecked")
			List<Book> list = (List<Book>) request.getSession().getAttribute(SESSION_LIST);
			for (Book book : list) {
				if (book.getIsbn().equalsIgnoreCase(isbn)) {
					Cart cart = (Cart) request.getSession().getAttribute(SESSION_CART);
					if (cart == null)
						cart = new Cart();
					
					if (command.equalsIgnoreCase(ADD_TO_CART_CMD))
						cart.addBookToCart(book);
					else if (command.equalsIgnoreCase(REMOVE_FROM_CART_CMD))
						cart.removeBookFromCart(book);
					else if (command.equalsIgnoreCase(UPDATE_CART_QUANTITY_CMD)) {
						String qtyString = request.getParameter(QUANTITY_CMD);
						if (qtyString != null && !qtyString.isEmpty()) {
							int qty = Integer.parseInt(qtyString);
							if (qty > 0)
								cart.updateQuantity(book, qty);
							else if (qty == 0)
								cart.removeBookFromCart(book);
						}
					}
					request.getSession().setAttribute(SESSION_CART, cart);
				}
			}
		}
	}

	private void createBookList(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<Book> list = (List<Book>) request.getSession().getAttribute(SESSION_LIST);
		if (list == null) {
			insertBooks();
			list = bookDatabaseAccessor.getSpecialBooks();
			request.getSession().setAttribute(SESSION_LIST, list);														
		}
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
		request.getSession().setAttribute(SESSION_USER, user);

		return user;
	}
	
	private void getBooksFromRequest (HttpServletRequest request) {
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
		
		request.getSession().setAttribute(SESSION_LIST, list);								
	}
	
	protected void insertBooks () {
		Book bookInRepository;
		
		Book book1 = new Book ("The Witness", "Nora Roberts", 16.00, 4.5,"00001", 101, Category.Romance,
				"Life and love catch up with a young woman seeking anonymity in the rural Ozarks.", false);
		bookInRepository = bookDatabaseAccessor.insertBook(book1);
		System.out.println(bookInRepository);
		book1 = new Book ("The Great Gatsby", "F. Scott Fitzgerald", 15.00, 3.6,"00002", 102, Category.Comedy,
				"The classic American novel of love lost and fortunes gained in the Jazz Age.", true);
		bookInRepository = bookDatabaseAccessor.insertBook(book1);
		System.out.println(bookInRepository);
		book1 = new Book ("The Forgotten", "David Baldacci", 14.99, 2.7,"00003", 103, Category.ScienceFiction,
				"The death of a beloved aunt leads Army Special Agent John Puller into a dangerous conspiracy.", false);
		bookInRepository = bookDatabaseAccessor.insertBook(book1);
		System.out.println(bookInRepository);
		book1 = new Book ("The Best of Me", "Nicholas Sparks", 14.99, 3.8,"00004", 104, Category.Romance,
				"A high school couple, separated 25 years later, rekindle their love after a friend’s funeral.", true);
		bookInRepository = bookDatabaseAccessor.insertBook(book1);
		System.out.println(bookInRepository);
		book1 = new Book ("The Lost Years", "Mary Higgins", 7.99, 4.9,"00005", 105, Category.History,
				"A scholar discovers a letter written by Jesus. Then the scholar is found dead.", false);
		bookInRepository = bookDatabaseAccessor.insertBook(book1);
		System.out.println(bookInRepository);
		book1 = new Book ("Delusion in Death", "J. D. Robb", 7.99, 2.6,"00006", 106, Category.Comedy,
				"Lt. Eve Dallas investigates the deaths of 80 people.", true);
		bookInRepository = bookDatabaseAccessor.insertBook(book1);
		System.out.println(bookInRepository);
		book1 = new Book ("The Wanderer", "Robyn Carr", 7.99, 3.9,"00007", 107, Category.Comedy,
				"A young vagabond inherits an Oregon property, and a community’s future lies in his hands.", true);
		bookInRepository = bookDatabaseAccessor.insertBook(book1);
		System.out.println(bookInRepository);
		book1 = new Book ("Now You See Her", "James Patterson", 10.00, 4.3,"00008", 108, Category.ScienceFiction,
				"To save an innocent man, a successful New York laywer must admit her life is a sham.", false);
		bookInRepository = bookDatabaseAccessor.insertBook(book1);
		System.out.println(bookInRepository);
		book1 = new Book ("Beautiful Ruins", "Jess Walter", 15.99, 4.7,"00009", 109, Category.Romance,
				"A love affair that begins on the Italian coast in 1962 is rekindled in Hollywood 50 years later.", true);
		bookInRepository = bookDatabaseAccessor.insertBook(book1);
		System.out.println(bookInRepository);
		book1 = new Book ("Sand Castle Bay", "Sherryl Woods", 7.99, 3.6,"00010", 110, Category.History,
				"Two long-separated lovers have a second chance together.", false);
		bookInRepository = bookDatabaseAccessor.insertBook(book1);
		System.out.println(bookInRepository);
	}

}
