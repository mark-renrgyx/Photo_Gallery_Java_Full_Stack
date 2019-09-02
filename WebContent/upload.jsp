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
	</form>

</body>
</html>