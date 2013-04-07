<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
</head>
<body>
	<%@ page import="bookstore.UserBean;"%>
	<table>
		<tr>
			<jsp:include page="./header.jsp" />
		</tr>
		<tr>
			<td>
				<form action="/BookStore/BookStoreServlet" method="post">
					<jsp:useBean id="userbean" scope="session"
						class="bookstore.UserBean" />
					<jsp:useBean id="addressbean" scope="session"
						class="bookstore.Address" />

					<table>
						<tr>
							<td align="right">
								<%
									if (userbean.isFirstNameMissing() == true) {
								%> <warn>*</warn> <%
 	}
 %>First name:
							</td>
							<td><input type="text" name="firstName"
								value="<jsp:getProperty name="userbean"
                      property="firstName"/>">
							</td>
						</tr>
						<tr>
							<td align="right">
								<%
									if (userbean.isLastNameMissing() == true) {
								%> <warn>*</warn> <%
 	}
 %>Last name:
							</td>
							<td><input type="text" name="lastName"
								value="<jsp:getProperty name="userbean"
                      property="lastName"/>">
							</td>
						</tr>
						<tr>

							<td align="right">
								<%
									if (userbean.isEmailAddressMissing() == true
											|| userbean.isEmailAddressDupl() == true) {
								%> <warn>*</warn> <%
 	}
 %>E-mail:
							</td>
							<td><input type="e-mail" name="emailAddress"
								value="<jsp:getProperty name="userbean"
                      property="emailAddress"/>">
								<%
									if (userbean.isEmailAddressDupl() == true) {
								%> <br /> <warn>
								<p>E-mail address already exists
								</warn> <%
 	}
 %></td>
						</tr>
						<tr>
							<td align="right">Password:</td>
							<td><input type="password" name="password"
								value="<jsp:getProperty name="userbean"
                      property="emailAddress"/>">
							</td>
						</tr>
						<tr>
							<td align="right">Address:</td>
							<td><input type="text" name="addrFirstLine"
								value="<jsp:getProperty name="userbean"
                      property="addrFirstLine"/>">
							</td>
						</tr>
						<tr>
							<td align="right">Address Line 2:</td>
							<td><input type="text" name="addrSecondLine"
								value="<jsp:getProperty name="userbean"
                      property="addrSecondLine"/>">
							</td>
						</tr>
						<tr>
							<td align="right">City:</td>
							<td><input type="text" name="addrCity"
								value="<jsp:getProperty name="userbean"
                      property="addrCity"/>">
							</td>
						</tr>
						<tr>
							<td align="right">State:</td>
							<td><input type="text" name="addrState"
								value="<jsp:getProperty name="userbean"
                      property="addrState"/>">
							</td>
						</tr>
						<tr>
							<td align="right">Zip:</td>
							<td><input type="text" name="addrZip"
								value="<jsp:getProperty name="userbean"
                      property="addrZip"/>">
							</td>
						</tr>
					</table>
					<%
						if (userbean.getUserStatus() != UserBean.UserStatus.NEW) {
					%>
					<input type="submit" value="Update User" /> <input type="hidden"
						name="command" value="UpdateUser" />
					<%
						} else {
					%>
					<input type="submit" value="Create User" /> <input type="hidden"
						name="command" value="CreateUser" />
					<%
						}
					%>

				</form>
			</td>
		</tr>
		<tr>
			<p>Footer</p>
		</tr>
	</table>
</body>
</html>