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
import org.springframework.stereotype.Component;

import bookstore.entity.User;
import bookstore.repository.UserRepository;
import bookstore.workerbeans.UserBean;
import bookstore.workerbeans.UserBeanImpl;

/**
 * Servlet implementation class BookStoreServlet
 */
@Component
@WebServlet(description = "Book Store Servlet for 605.782 Class Project", urlPatterns = { "/BookStoreServlet" })
public class BookStoreServlet extends HttpServlet {
	
	@Autowired
	private UserBean userBean;
	
	private final static String MAIN_JSP = "/main.jsp";
	private final static String USER_PROFILE_JSP = "/user_profile.jsp";
	
	private static final long serialVersionUID = 1L;
       
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
		String url = "/main.jsp";
		User dbUser = null;
		
		command = request.getParameter("command");
		
		System.out.println("Command: "+ command);
		
		if (command != null) {
			if (command.equalsIgnoreCase("CreateUser")) {

				try{
					dbUser = userBean.updateUser(request, response);
					url = MAIN_JSP;
				}catch(Exception e){
					url = USER_PROFILE_JSP;
				}
				
				request.getSession().setAttribute("userbean", dbUser);
			}
			else if (command.equalsIgnoreCase("UpdateUser")) {
				try{
					dbUser = userBean.insertUser(request, response);
					url = MAIN_JSP;
				}catch(Exception e){
					url = USER_PROFILE_JSP;
				}
				request.getSession().setAttribute("userbean", dbUser);				
			}else{
				throw new IllegalArgumentException("The command in the servlet didn't match anything.");
			}
		}
		System.out.println("url: " + url);
		System.out.println(getServletContext().toString());

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
