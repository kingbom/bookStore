<%@ page import="bookstore.entity.Book.Category"%>
<%@ page import="bookstore.entity.Book"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.ArrayList;"%>

<%
	DecimalFormat df = new DecimalFormat();
	df.setMinimumFractionDigits(2);
	df.setMaximumFractionDigits(2);

	ArrayList<Book> bookList = null;
	if (session.getAttribute("booklist") != null) {
		bookList = (ArrayList<Book>) session.getAttribute("booklist");
	} else {
%>
<script>
	window.location = "./BookStoreServlet?command=DisplayMainPage&category=OnSpecial";
</script>
<%
	}
%>

<%
	if (bookList != null) {
		if (bookList.isEmpty() == false) {
%>
<table class="bookList">
	<%
		if(request.getSession().getAttribute("category") !=null) {
	%>
		<h4><%=request.getSession().getAttribute("category") %></h4>
	<% } else { %>
		
	<% } %>
	<%
		for (Book book : bookList) {
	%>
	<tr>
		<td width=400px valign="top"><span class="price">$<%=df.format(book.getPrice())%></span>
		<h5><%=book.getTitle()%></h5>
			<h6><%=book.getAuthor()%></h6>
			<p>
				<%=book.getDescription()%>
			</p> <br></td>
		<td align="center" valign="top"><div>
				<img class="bookImage"
					src="./resources/images/books/<%=book.getImageFileName()%>" /><br />
				<form
					action="/bookstore/BookStoreServlet?command=AddToCart&ISBN=<%=book.getIsbn()%>"
					method="post">
					<input type="submit" value="Add to cart"></input>
				</form>
			</div></td>

	</tr>

	<%
		}
	%>

</table>
<%
	} else {
%>
<h5>No results found</h5>
<%
	}
	}
%>
