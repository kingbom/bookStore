<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="./resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="./resources/booksrus.css">

<title>BooksRUs</title>
</head>
<!-- 
categories: Comedy, History, Romance, ScienceFiction
/BookStoreServlet?command=DisplayMainPage&category=Comedy
command=DisplayMainPage?category=OnSpecial
command=DisplayMainPage?search=<search string
booklist all lower case session object> 
-->
<body>
	<table width=800px>
		<tr>
			<td colspan=2><jsp:include page="./header.jsp" /><br/></td>
		</tr>
		<tr>
			<td width="150px" valign="top" align="left"><jsp:include page="./leftColumn.jsp" /></td>
			<td width="650px" valign="top" align="left"><jsp:include page="./content.jsp" /></td>
		</tr>
		<tr>
			<td colspan=2>
				<p>Footer</p>
			</td>
		</tr>
	</table>
</body>
</html>