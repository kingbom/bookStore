package bookstore;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bookstore.entity.User;
import bookstore.entity.User.UserStatus;
import bookstore.workerbeans.UserDatabaseAccessor;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginCheckoutServlet")
public class LoginCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//@Autowired
	UserDatabaseAccessor userDatabaseAccessor;
	
	ClassPathXmlApplicationContext springContext;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String contextLocation = "WEB-INF/spring/applicationContext.xml";
		//String contextLocation = "file:src/main/webapp/WEB-INF/spring/applicationContext.xml";
		springContext = new ClassPathXmlApplicationContext(contextLocation);
		userDatabaseAccessor=springContext.getBean(UserDatabaseAccessor.class);
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
		String url;
		String userName;
		User user;
		
		url = BookStoreServlet.CHECKOUT_JSP;
		userName = request.getRemoteUser();
		user = userDatabaseAccessor.getUser(userName);
		System.out.println("LoginServlet - url: " + url + " userName:" + userName + " user:" + user);
		user.setUserStatus(UserStatus.LOGGED_IN);
		initializeBillingAddress(request);
		request.getSession().setAttribute(BookStoreServlet.SESSION_USER, user);


		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
	
	private void initializeBillingAddress(HttpServletRequest request) {
		User user;
		CreditCard card;
		user = getUserFromSession(request);
		card = getCreditCardFromSession(request);
		if (card.isAddressFirstLineEmpty())
			card.setAddressFirstLine(user.getAddressFirstLine());
		if (card.isAddressSecondLineEmpty())
			card.setAddressSecondLine(user.getAddressSecondLine());
		if (card.isCityEmpty())
			card.setCity(user.getCity());
		if (card.isStateEmpty())
			card.setState(user.getState());
		if (card.isZipcodeEmpty())
			card.setZipcode(user.getZipcode());
		request.getSession().setAttribute(BookStoreServlet.SESSION_CREDIT_CARD, card);
	}

	private CreditCard getCreditCardFromSession(HttpServletRequest request) {
		CreditCard card = (CreditCard) request.getSession().getAttribute(BookStoreServlet.SESSION_CREDIT_CARD);
		if (card == null)
			card = new CreditCard();
		return card;
	}

	private User getUserFromSession(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(BookStoreServlet.SESSION_USER);
		if(user == null) {
			user = new User();
		}
		return user;
	}
}
