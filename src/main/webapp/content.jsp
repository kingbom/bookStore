<%@ page import="bookstore.entity.Book.Category"%>
<%@ page import="bookstore.entity.Book"%>
<%@ page import="java.util.ArrayList;"%>

<%
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
<table>

	<%
		for (Book book : bookList) {
	%>
	<tr>
		<td width=400px>Title: <%=book.getTitle()%> <br> Author: <%=book.getAuthor()%>
			<br> Description: <%=book.getDescription() %> <br> 
		</td>
		<td><img src="./resources/images/books/<%=book.getImageFileName() %>"/><br>$<%=book.getPrice() %><br><a href="/bookstore/BookStoreServlet?command=AddToCart&ISBN=<%=book.getIsbn()%>">Add to cart</a></td>
	<tr>

		<%
			}
		%>
	
</table>
<%
	} else
%>
<p>No results returned</p>
<%
	}
%>
