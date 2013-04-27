<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css"
	href="./resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="./resources/booksrus.css">
<title>Shopping Cart</title>
</head>
<body>
	<%@ page import="bookstore.Cart"%>
	<%@ page import="java.util.Iterator"%>
	<%@ page import="bookstore.entity.Book"%>
	<%@ page import="java.text.DecimalFormat" %>
	<%@ page import="java.util.Map"%>

	<%
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);

		Cart cart = null;

		if (session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
	%>
	<table width=800px>
		<tr>
			<td colspan=2><jsp:include page="./header.jsp" /><br /></td>
		</tr>
		<tr>
			<td width="150px" valign="top" align="left"><jsp:include
					page="./leftColumn.jsp" /></td>
			<td width="650px" valign="top" align="left">
				<h4>Shopping Cart</h4>
				<table class="shoppingCart">
					<tr align="left">
						<th width="150px">Title</th>
						<th width="50px">Price</th>
						<th width="170px">Quantity</th>
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
						<td>
							<form
								action="/bookstore/BookStoreServlet?command=UpdateCartQuantity&ISBN=<%=entry.getKey().getIsbn()%>"
								method="post">
								<input type="text" maxlength="5em" size="5" name="quantity"
									value="<%=entry.getValue()%>" /> <input type="submit"
									value="Update Qty" />
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
						<td>$<%=df.format(cart.getTotal())%></td>
						<td />
					</tr>

				</table>
				<form action="/bookstore/LoginCheckoutServlet"
					method="post">
					<input type="submit" value="Checkout" />

				</form> <a href="./main.jsp">Continue Shopping</a>


			</td>
		</tr>
		<tr>
			<td colspan=2>
				<p>Footer</p>
			</td>
		</tr>
	</table>
	<%
		} else {
	%>
	<table width=800px>
		<tr>
			<td colspan=2><jsp:include page="./header.jsp" /><br /></td>
		</tr>
		<tr>
			<td width="150px" valign="top" align="left"><jsp:include
					page="./leftColumn.jsp" /></td>
			<td width="650px" valign="top" align="left"><h4>No items in
					cart</h4></td>
		</tr>
		<tr>
			<td colspan=2>
				<p>Footer</p>
			</td>
		</tr>
	</table>

	<%
		}
	%>

</body>
</html>