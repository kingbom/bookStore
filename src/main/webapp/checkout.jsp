<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="./resources/bootstrap/css/bootstrap.css">
<title>Checkout</title>
</head>
<body>
	<%@ page import="bookstore.CreditCard"%>

	<form action="/bookstore/BookStoreServlet">
		<jsp:useBean id="card" scope="session" class="bookstore.CreditCard" />
		<table>
			<tr>
				<td align="right">
					<%
						if (card.isAddressFirstLineMissing()) {
					%> <warn>*</warn> <%
 	}
 %>Address:
				</td>
				<td><input type="text" name="addressFirstLine"
					value="<jsp:getProperty name="card"
                      property="addressFirstLine"/>">
				</td>
			</tr>
			<tr>
				<td align="right">
					<%
						if (card.isAddressSecondLineMissing()) {
					%>Address(Second Line): <%
						}
					%>
				</td>
				<td><input type="text" name="addressSecondLine"
					value="<jsp:getProperty name="card"
                      property="addressSecondLine"/>">
				</td>
			</tr>
			<tr>

				<td align="right">
					<%
						if (card.isCityMissing()) {
					%> <warn>*</warn> <%
 	}
 %>City:
				</td>
				<td><input type="text" name="city"
					value="<jsp:getProperty name="card"
                      property="city"/>">
				</td>
			</tr>
			<tr>

				<td align="right">
					<%
						if (card.isStateMissing()) {
					%> <warn>*</warn> <%
 	}
 %>State:
				</td>
				<td><input type="text" name="state"
					value="<jsp:getProperty name="card"
                      property="state"/>">
				</td>
			</tr>
			<tr>

				<td align="right">
					<%
						if (card.isZipcodeMissing()) {
					%> <warn>*</warn> <%
 	}
 %>Zip Code:
				</td>
				<td><input type="text" name="zipcode"
					value="<jsp:getProperty name="card"
                      property="zipcode"/>">
				</td>
			</tr>
			<tr>

				<td align="right">
					<%
						if (card.isCreditCardTypeMissing()) {
					%> <warn>*</warn> <%
 	}
 %>Card Type:
				</td>
				<td><input type="text" name="creditCardType"
					value="<jsp:getProperty name="card"
                      property="creditCardType"/>">
				</td>
			</tr>
			<tr>

				<td align="right">
					<%
						if (card.isCardNumberMissing()) {
					%> <warn>*</warn> <%
 	}
 %>Card Number:
				</td>
				<td><input type="text" name="cardNumber"
					value="<jsp:getProperty name="card"
                      property="cardNumber"/>">
				</td>
			</tr>
						<tr>

				<td align="right">
					<%
						if (card.isExpDateMissing()) {
					%> <warn>*</warn> <%
 	}
 %>Exp Month:
				</td>
				<td><input type="text" name="expMonth"
					value="<jsp:getProperty name="card"
                      property="expMonth"/>">
				</td>
			</tr>
						<tr>

				<td align="right">
					<%
						if (card.isExpDateMissing()) {
					%> <warn>*</warn> <%
 	}
 %>Exp Year:
				</td>
				<td><input type="text" name="expYear"
					value="<jsp:getProperty name="card"
                      property="expYear"/>">
				</td>
			</tr>
		</table>

		<input type="submit" value="Complete Checkout" /> <input
			type="hidden" name="command" value="CompleteCheckout" />
			

	</form>
</body>
</html>