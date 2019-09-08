<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/css/site_styles.css" %></style>
<link href='http://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>
<meta charset="ISO-8859-1">
<title>Upload</title>
</head>
<body>
<!-- Once logged on, show photos and functionality -->

	<h2>Choose file to upload</h2>
	
	<form action="FileUploadHandler" method="post" enctype="multipart/form-data">
		<input type="file" name="file" /> 
		
		<select name="category">
			<option value="Events">Events</option>
			<option value="People">People</option>
			<option value="Animals">Animals</option>
			<option value="Places">Places</option>
			<option value="Food">Food</option>
			<option value="Things">Things</option>
			<option value="Other">Other</option>
		</select> <br/>
		<input type="submit" value="upload" />
	</form>

</body>
</html>