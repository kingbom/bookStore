<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css"
	href="./resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="./resources/booksrus.css">
<title>Order Confirmation</title>
</head>
<body>
	<%@ page import="bookstore.Cart"%>

	<%@ page import="bookstore.CreditCard"%>
	<%@ page import="java.util.Iterator"%>
	<%@ page import="java.util.Map"%>
	<%@ page import="bookstore.entity.Book"%>
	<%@ page import="java.text.DecimalFormat"%>



	<%
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);

		Cart cart = null;

		if (session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
		}
	%>
	<jsp:include page="./header.jsp" />

	<h4>Order Confirmation</h4>
	<p>Thank you for shopping with us!</p>
	<p>Your order contains the following item(s):</p>

	<h4>Items</h4>
	<table class="shoppingCart">
		<jsp:useBean id="card" scope="session" class="bookstore.CreditCard" />
		<jsp:useBean id="user" scope="session" class="bookstore.entity.User" />

		<tr align="left">
			<th width=200px>Title</th>
			<th width=50px>Price</th>
			<th>Quantity</th>
		</tr>
		<%
			Double grandTotal = 0.0;
			Iterator<Map.Entry<Book, Integer>> bookIterator = cart
					.getIterator();
			while (bookIterator.hasNext()) {
				Map.Entry<Book, Integer> entry = bookIterator.next();
		%>
		<tr>
			<td><%=entry.getKey().getTitle()%></td>
			<td>$<%=df.format(entry.getKey().getPrice())%> <%
 	grandTotal += entry.getKey().getPrice() * entry.getValue();
 %></td>
			<td><%=entry.getValue()%></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td />
			<td>$<%=df.format(cart.getTotal())%></td>
			<td />
		</tr>
	</table>
	<table>
		<tr>
			<h4>Shipping Info</h4>
		</tr>
		<tr>
			<td align="right">First Name:</td>
			<td><jsp:getProperty name="user" property="firstName" /></td>
		</tr>
		<tr>
			<td align="right">Last Name:</td>
			<td><jsp:getProperty name="user" property="lastName" /></td>
		</tr>
		<tr>
			<td align="right">Address:</td>
			<td><jsp:getProperty name="card" property="addressFirstLine" />
			</td>
		</tr>
		<tr>
			<td align="right">Address(Second Line):</td>
			<td><jsp:getProperty name="card" property="addressSecondLine" />
			</td>
		</tr>
		<tr>

			<td align="right">City:</td>
			<td><jsp:getProperty name="card" property="city" /></td>
		</tr>
		<tr>

			<td align="right">State:</td>
			<td><jsp:getProperty name="card" property="state" /></td>
		</tr>
		<tr>

			<td align="right">Zip Code:</td>
			<td><jsp:getProperty name="card" property="zipcode" /></td>
		</tr>
		<tr>
			<td align="right">Card Type:</td>
			<td><jsp:getProperty name="card" property="creditCardType" /></td>
		</tr>
	</table>
	<br/>
	<a href="./BookStoreServlet?command=NewCart">Continue Shopping</a>
</body>
</html>