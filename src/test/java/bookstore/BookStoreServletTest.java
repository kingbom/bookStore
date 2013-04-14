package bookstore;


import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.cglib.proxy.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bookstore.BookStoreServlet;
import bookstore.entity.User;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
public class BookStoreServletTest {

	@Autowired
	private BookStoreServlet bookStoreServlet;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testThatCreateUserCommandCreatesAUser() throws Exception {
		User patrick = new User("847 Mapple Dr.", "", "Washington", "Patrick@gmail.com", "Patrick", "Steward345345", "moneyMonkey", "MD","64541");
		
		HttpServletRequest requestMock = mock(HttpServletRequest.class);
		HttpServletResponse responseMock = mock(HttpServletResponse.class);
		HttpSession sessionMock = mock(HttpSession.class);
		ServletContext contextMock = mock(ServletContext.class);
		RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
		
		when( requestMock.getParameter("command")).thenReturn("CreateUser");
		when(requestMock.getSession()).thenReturn(sessionMock);			
		when(sessionMock.getAttribute("userbean")).thenReturn(patrick);
		//getServletContext().getRequestDispatcher(url)
		BookStoreServlet bookStoreServletSpy = spy(bookStoreServlet);
		doReturn(contextMock).when(bookStoreServletSpy).getServletContext();
		when(contextMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		bookStoreServletSpy.doPost(requestMock, responseMock);
		verify(sessionMock).setAttribute("userbean", patrick);
		verify(dispatcherMock).forward(requestMock, responseMock);
	}

}
