<table width="800px" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width=200px><img id="mainLogo" src="./resources/images/booksrus.jpg" /></td>
		<td width=300px></td>
		<td width=300px valign="top">
			<table>
				<tr>
					<td>
						<form action="./BookStoreServlet" method="post">
							<input type="search" name="search" size="20em" /> <input
								type="submit" value="Search" /> <input type="hidden"
								name="command" value="DisplayMainPage" />
						</form>
					</td>
				</tr>
				<tr>
					<td><a href="./login.jsp">Login</a> |
					<a href="./display_cart.jsp">Cart</a> | <a href="./user_profile.jsp">My Profile</a>
					</td>
			</tr>
			</table>
		</td>
	</tr>
</table>
