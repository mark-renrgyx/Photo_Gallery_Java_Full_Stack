<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- Once logged on, show photos and functionality -->

	<h3>Choose File to Upload in Server</h3>
	<form action="FileUploadHandler" method="post" enctype="multipart/form-data">
		<input type="file" name="file" /> <input type="submit" value="upload" />
		<br/>
		<select name="category">
			<option value="Events">Events</option>
			<option value="People">People</option>
			<option value="Animals">Animals</option>
			<option value="Places">Places</option>
			<option value="Food">Food</option>
			<option value="Things">Things</option>
			<option value="Other">Other</option>
		</select>
	</form>

</body>
</html>