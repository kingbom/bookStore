<%@page import="bookstore.entity.User.UserStatus"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="./resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="./resources/booksrus.css">
<title>User Profile</title>
</head>
<body>
	<%@ page import="bookstore.entity.User;"%>

	<table width=800px>
		<tr>
			<td colspan=2><jsp:include page="./header.jsp" /><br /></td>
		</tr>
		<tr>
			<td width="150px" valign="top" align="left"><jsp:include
					page="./leftColumn.jsp" /></td>
			<td width="650px" valign="top" align="left">
				<h4>User Profile</h4>
				<table>
					<tr>
						<td>
							<form action="/bookstore/BookStoreServlet" method="post">
								<jsp:useBean id="user" scope="session"
									class="bookstore.entity.User" />

								<%
									if (user.getUserStatus().equals(UserStatus.INVALID)) {
								%>
									<h5><warn>Please fill in required fields</warn></h5>
								<%
									}
								%>
								<table>
									<tr>
										<td align="right">
											<%
												if (user.getFirstName() == "") {
											%> <warn>*</warn> <%
 	}
 %>First name:
										</td>
										<td><input type="text" name="firstName"
											value="<jsp:getProperty name="user"
                      property="firstName"/>">
										</td>
									</tr>
									<tr>
										<td align="right">
											<%
												if (user.getLastName() == "") {
											%> <warn>*</warn> <%
 	}
 %>Last name:
										</td>
										<td><input type="text" name="lastName"
											value="<jsp:getProperty name="user"
                      property="lastName"/>">
										</td>
									</tr>
									<tr>

										<td align="right">
											<%
												if (user.getEmail() == "") {
											%> <warn>*</warn> <%
 	}
 %>E-mail:
										</td>
										<td><input type="email" name="emailAddress"
											value="<jsp:getProperty name="user"
                      property="email"/>">
										</td>
									</tr>
									<tr>
										<td align="right">Password:</td>
										<td><input type="password" name="password"
											value="<jsp:getProperty name="user"
                      property="password"/>">
										</td>
									</tr>
									<tr>
										<td align="right">Address:</td>
										<td><input type="text" name="addressFirstLine"
											value="<jsp:getProperty name="user"
                      property="addressFirstLine"/>">
										</td>
									</tr>
									<tr>
										<td align="right">Address Line 2:</td>
										<td><input type="text" name="addressSecondLine"
											value="<jsp:getProperty name="user"
                      property="addressSecondLine"/>">
										</td>
									</tr>
									<tr>
										<td align="right">City:</td>
										<td><input type="text" name="city"
											value="<jsp:getProperty name="user"
                      property="city"/>">
										</td>
									</tr>
									<tr>
										<td align="right">State:</td>
										<td><input type="text" name="state"
											value="<jsp:getProperty name="user"
                      property="state"/>">
										</td>
									</tr>
									<tr>
										<td align="right">Zip:</td>
										<td><input type="text" name="zipcode"
											value="<jsp:getProperty name="user"
                      property="zipcode"/>">
										</td>
									</tr>
								</table>
								<%
									if (user.getId() != null) {
								%>
								<input type="submit" value="Update User" /> <input
									type="hidden" name="command" value="UpdateUser" />
								<%
									} else {
								%>
								<input type="submit" value="Create User" /> <input
									type="hidden" name="command" value="CreateUser" />
								<%
									}
								%>

							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=2>
				<p>Footer</p>
			</td>
		</tr>
	</table>
</body>
</html>