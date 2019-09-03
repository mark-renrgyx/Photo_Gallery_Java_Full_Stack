<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="site_styles.css" />

<title>Gallery Landing</title>
</head>
<body>
	<!-- Check for logon, if so, redirect, if not this is logon page. -->
	
<noscript><h1>You should enable Javascript to enjoy the full awesomeness of this page</h1></noscript>

<h1>Welcome to <em>the</em> Web Gallery</h1>

<!--  TODO: Should check if logged in, if so redirect to home.jsp -->

<%
	if (request.getSession().getAttribute("loggedIn") != null && request.getSession().getAttribute("loggedIn").equals(true)) {
		response.sendRedirect("home.jsp");
	}
	else {
		request.getSession().setAttribute("loggedIn", Boolean.valueOf(false));
	}
%>

<p>You should log in!</p>
<form action="Login" method="post">
Email Address<input type="text" name="email"> <br />
Password<input type="text" name="password"> <br />
<input type="submit" value="Login">
</form>

<p>Don't have an account yet?  Make one <a href="registration.jsp">here</a></p>

</body>
</html>