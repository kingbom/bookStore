package bookstore;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookStoreServlet
 */
@WebServlet(description = "Book Store Servlet for 605.782 Class Project", urlPatterns = { "/BookStoreServlet" })
public class BookStoreServlet extends HttpServlet {
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
		// TODO Auto-generated method stub
		String command;
		UserBean userBean;
		String url = "/main.jsp";
		
		command = request.getParameter("command");
		
		System.out.println("Command: "+ command);
		
		if (command != null) {
			if (command.equalsIgnoreCase("CreateUser")) {
				
				userBean = updateUser(request, response);
				userBean.validateUser();
				if (userBean.isUserValid()) {
					userBean.createUser();
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
					userBean.updateUser();
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
	
	private UserBean updateUser (HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Email: " + request.getParameter("emailAddress"));
		
		UserBean userBean = (UserBean) request.getSession().getAttribute("userbean");
		if(userBean == null) {
			userBean = new UserBean();
		}
		userBean.setEmailAddress(request.getParameter("emailAddress"));
		userBean.setFirstName(request.getParameter("firstName"));
		userBean.setLastName(request.getParameter("lastName"));
		userBean.setPassword(request.getParameter("password"));
		userBean.setAddrFirstLine(request.getParameter("addrFirstLine"));
		userBean.setAddrSecondLine(request.getParameter("addrSecondLine"));
		userBean.setAddrCity(request.getParameter("addrCity"));
		userBean.setAddrState(request.getParameter("addrState"));
		userBean.setAddrZip(request.getParameter("addrZip"));

		return userBean;
	}

}
