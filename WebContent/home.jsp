<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.sql.ResultSet"%>
<%@page import="db.DBUtility"%>
<%@page import="db.DBConnection"%>
<%@page import="java.sql.Connection"%>

<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Iterator" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/css/site_styles.css" %></style>

<link href='http://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">

<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<title>Gallery</title>
</head>
<body>

<div class="gallery_container">
	<h1>Your super-awesome gallery</h1>
	<div id="controls">
		<div class="option">
			<a href="upload.jsp">Upload a file</a>
		</div>
		<div class="option">
			<form action="Search">
				<input type="text" name="search">
				<input type=submit value="Search">
			</form>
		</div>
		<div class="option">
			<form action="Filter" method="post">
		        <select name="category">
		            <option value="Events">Events</option>
		            <option value="People">People</option>
		            <option value="Animals">Animals</option>
		            <option value="Places">Places</option>
		            <option value="Food">Food</option>
		            <option value="Things">Things</option>
		            <option value="Other">Other</option>
		        </select>
		        <input type="submit" value="Filter by category">
    		</form>
		</div>
		<div class="option">
			<form action="Logout" method="post">
        		<input type="submit" value="Logout" />
    		</form>
		</div>
	</div>
	
	<%
		List<String> dbImages = DBUtility.getImages();
		
		Iterator<String> imageCursor = dbImages.iterator();
		
		String taggedImages = "";
		while (imageCursor.hasNext()) {
			taggedImages += "<div class='thumbnail_container resizable'><img class='thumbnail' src='";
			taggedImages += imageCursor.next();
			taggedImages += "'></div> \n";
		}
		// <div class='little_handle ui-resizable-se ui-resizable-handle'></div>
	%>

	<div class="gallery">
		<%=taggedImages%>
	</div>
</div>

<script>
  $( function() {
    $( ".resizable" ).resizable({
    	autoHide: true,
    	aspectRatio: true
    });
  } );
</script>
</body>
</html>