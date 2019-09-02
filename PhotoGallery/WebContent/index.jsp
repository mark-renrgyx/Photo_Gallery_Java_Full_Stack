<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- NOTE deleted META-INT/context.xml -->

<html>
<head>
<meta charset="UTF-8">
<title>Gallery Landing</title>
<link rel="stylesheet" type="text/css" href="site_styles.css" />
<link rel="stylesheet" type="text/css" href="index_styles.css" />
</head>
<body>

<noscript><h1>You should enable Javascript to enjoy the full awesomeness of this page</h1></noscript>

<h1>Welcome to <em>the</em> Web Gallery</h1>

<!--  TODO: Should check if logged in, if so redirect to home.jsp -->

<p>You should log in!</p>
<form action="Login" method="post">
Email Address<input type="text" name="email" value="test"> <br />
Password<input type="text" name="password"> <br />
<input type="submit">
</form>

</body>
</html>