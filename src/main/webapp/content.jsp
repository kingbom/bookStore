<%@ page import="bookstore.entity.Book.Category"%>
<%@ page import="bookstore.entity.Book"%>
<%@ page import="java.util.ArrayList;"%>

<%
	ArrayList<Book> bookList;
	if (session.getAttribute("BookList") != null) {
		bookList = (ArrayList<Book>) session.getAttribute("BookList");
	} else {
		bookList = new ArrayList<Book>();
	}

	Book e = new Book();
	e.setTitle("Some book");
	e.setAuthor("Award Winning Author");
	e.setDescription("asdf dfasd dnfoe asdoifjn nsdoifjaeoijon odjj aosidjf asiod nasdoifio sod fsdj iojdfio daofd x asdf eoi djofjb aodn oeinr eoinsinfd  jsdifjounfbgyd dfasdf sdx jru dasdf.");
	e.setPageCount(201);
	e.setPrice(34.99);
	e.setRating(4.5);
	e.setCategory(Category.Comedy);

	bookList.add(e);
	bookList.add(e);
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
		<td>Some Picture<br>$<%=book.getPrice() %></td>
	<tr>

		<%
			}
		%>
	
</table>
<%
	}
	}
%>
