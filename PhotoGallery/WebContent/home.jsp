<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Iterator" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="site_styles.css" />
<title>Upload</title>
</head>
<body>
<h1>Your super-awesome gallery</h1>

<a href="upload.jsp">Upload a file</a>

<%
	// dummy in files
	List<String> images = new ArrayList<String>();
	images.add("img/dummypic1.jpeg");
	images.add("img/dummypic2.png");
	images.add("img/dummypic3.jpeg");
	images.add("img/dummypic4.jpg");
	images.add("img/dummypic5.jpeg");
	images.add("img/dummypic6.jpeg");
	images.add("img/dummypic7.jpeg");
	images.add("img/dummypic1.jpeg");
	images.add("img/dummypic2.png");
	images.add("img/dummypic3.jpeg");
	images.add("img/dummypic4.jpg");
	images.add("img/dummypic5.jpeg");
	images.add("img/dummypic6.jpeg");
	images.add("img/dummypic7a.jpeg");
	
	// The iterator we might actually use
	Iterator<String> imageCursor = images.iterator();
%>

<%
	String taggedImages = "";
	while (imageCursor.hasNext()) {
		taggedImages += "<img class='thumbnail' src='";
		taggedImages += imageCursor.next();
		taggedImages += "'> \n";
	}
%>

<div class="gallery">
	<%= taggedImages %>
</div>

</body>
</html>