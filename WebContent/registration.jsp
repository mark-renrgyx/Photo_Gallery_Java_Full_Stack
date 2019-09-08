<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/css/site_styles.css" %></style>
<link href='http://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>

<h2>Registration</h2>
<!-- Where they register new users, redirect to home if successful, reload if not -->

<form action="Register" method="post">
Name<input type="text" name="name"> <br/>
Email Address<input type="text" name="email"> <br/>
Password<input type="text" name="password"> <br />
<input type="submit">
</form>

</body>
</html>