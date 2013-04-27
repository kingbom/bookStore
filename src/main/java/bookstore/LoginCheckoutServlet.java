package bookstore;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.entity.User;
import bookstore.entity.User.UserStatus;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginCheckoutServlet")
public class LoginCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
//TODO = plug in call to retrieve user from the DB and initialize the billing address
		user = new User();
		user.setEmail(userName);
		user.setUserStatus(UserStatus.LOGGED_IN);
		request.getSession().setAttribute(BookStoreServlet.SESSION_USER, user);

		System.out.println("LoginServlet - url: " + url + " userName:" + userName);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
