<%@ page import="bookstore.entity.Book.Category"%>
<%@ page import="bookstore.entity.Book"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.ArrayList;"%>

<%
	DecimalFormat df = new DecimalFormat();
	df.setMinimumFractionDigits(2);
	df.setMaximumFractionDigits(2);

	ArrayList<Book> bookList;
	if (session.getAttribute("booklist") != null) {
		bookList = (ArrayList<Book>) session.getAttribute("booklist");
	} else {
		bookList = new ArrayList<Book>();

		Book e = new Book("521");
		e.setTitle("Some book");
		e.setAuthor("Award Winning Author");
		e.setDescription("asdf dfasd dnfoe asdoifjn nsdoifjaeoijon odjj aosidjf asiod nasdoifio sod fsdj iojdfio daofd x asdf eoi djofjb aodn oeinr eoinsinfd  jsdifjounfbgyd dfasdf sdx jru dasdf.");
		e.setPageCount(201);
		e.setPrice(34.99);
		e.setRating(4.5);
		e.setCategory(Category.Comedy);

		bookList.add(e);
		bookList.add(e);
	}
%>

<%
	if (bookList != null) {
		if (bookList.isEmpty() == false) {
%>
<table class="bookList">

	<%
		for (Book book : bookList) {
	%>
	<tr>
		<td width=400px valign="top"><span class="price">$<%=df.format(book.getPrice())%></span><h5><%=book.getTitle()%></h5>
			<h6><%=book.getAuthor()%></h6>
			<p>
				<%=book.getDescription()%>
			</p>
			<br></td>
		<td align="center" valign="top"><div><img class="bookImage"
			src="./resources/images/books/<%=book.getImageFileName()%>" /><br/>
			<form
				action="/bookstore/BookStoreServlet?command=AddToCart&ISBN=<%=book.getIsbn()%>"
				method="post">
				<input type="submit" value="Add to cart"></input>
			</form>
			</div>
			</td>
			
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
