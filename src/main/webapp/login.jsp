<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<jsp:include page="./header.jsp" />
		</tr>
		<tr>
			<td>
				<form name="login"
					action="<%=response.encodeURL("/bookstore/BookStoreServlet")%>"
					method="post">
					<table>
						<tr>
							<td align="right">E-Mail Address:</td>
							<td><input type="text" name="emailAddress" /></td>
						</tr>
						<tr>
							<td align="right">Password:</td>
							<td><input type="password" name="password" /></td>
						</tr>
					</table>
					<input type="submit" value="Login">
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<form name="newUser"
					action="<%=response.encodeURL("/bookstore/user_profile.jsp")%>"
					method="post">
					<input type="hidden" name="command" value="CreateUser" /> <input
						type="submit" value="Create User">
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<p>Footer</p>
			</td>
		</tr>
	</table>
</body>
</html>