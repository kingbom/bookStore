<%@ page import="bookstore.entity.User;"%>
<table width="800px" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width=200px><a href="./BookStoreServlet?command=DisplayMainPage&category=OnSpecial"><img id="mainLogo"
			src="./resources/images/booksrus.jpg" /></a></td>
		<td width=250px></td>
		<td width=250px valign="top">
			<%
				User user = null;
				if (session.getAttribute("user") != null) {
					user = (User) session.getAttribute("user");
				}
			%>
			<table>
				<tr>
					<td>
						<form action="./BookStoreServlet" method="post">
							<input type="search" name="search" size="20em" /> <input
								type="submit" value="Search" /> <input type="hidden"
								name="command" value="DisplayMainPage" />
						</form>
					</td>
				</tr>
				<tr>
					<td>
						<%
							if (user != null) {
								if (user.isUserLoggedIn()) {
						%> Logged in as <%=user.getEmail()%> <%
 	} else {
 %> <%
 	}
 	} else {
 %> <%
 	}
 %>
					</td>
				</tr>
				<tr>
					<td>
						<%
							if (user != null) {
								if (user.isUserLoggedIn()) {
						%> <a href="./BookStoreServlet?command=Logout">Log out</a> <%
 	} else {
 %> <a href="./LoginServlet">Log in</a> <%
 	}
 	} else {
 %><a href="./LoginServlet">Log in</a> <%
 	}
 %> | <a href="./display_cart.jsp">Cart</a> | <a
						href="./user_profile.jsp">My Profile</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
