<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="./resources/bootstrap/css/bootstrap.css">
<title>Shopping Cart</title>
</head>
<body>
	<%@ page import="bookstore.Cart"%>
	<%@ page import="java.util.Iterator"%>
	<%@ page import="bookstore.entity.Book"%>
	<%@ page import="java.util.Map"%>

	<%
		Cart cart = null;

		if (session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
		}
	%>
	<table>
		<tr align="left">
			<th>Title</th>
			<th>Price</th>
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
			<td><%=entry.getKey().getPrice()%> <%
 	grandTotal += entry.getKey().getPrice() * entry.getValue();
 %></td>
			<td>
				<form
					action="/bookstore/BookStoreServlet?command=UpdateCartQuantity&ISBN=<%=entry.getKey().getIsbn()%>"
					method="post">
					<input type="text" maxlength="5em" size="5"  name="quantity" value="<%=entry.getValue()%>" />
					<input type="submit" value="Update Qty" />
				</form>
			</td>
			<td>
				<form
					action="/bookstore/BookStoreServlet?command=RemoveFromCart&ISBN=<%=entry.getKey().getIsbn()%>"
					method="post">
					<input type="submit" value="Remove">
				</form>
			</td>
		</tr>
		<%
			}
		%>
		<tr>
			<td />
			<td><%=Math.floor(grandTotal * 100) / 100%></td>
			<td />
		</tr>

	</table>
	<form action="/bookstore/BookStoreServlet?command=DisplayCheckout" method="post">
		<input type="submit" value="Checkout"/>
		
	</form>
	<a href="./main.jsp">Continue Shopping</a>
</body>
</html>