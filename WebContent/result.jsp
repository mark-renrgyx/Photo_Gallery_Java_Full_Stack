<%@page import="java.sql.ResultSet"%>
<%@page import="db.DBUtility"%>
<%@page import="db.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="result">
		<h3>${requestScope["message"]}</h3>
	</div>
	<br />

	<%
		Connection con = DBConnection.getDBInstance();
		DBUtility.useDB(con, "gallery");
		String query, selectQuery;
		selectQuery = "SELECT * FROM image;";
		ResultSet rs;
		rs = DBUtility.executeQuery(con, selectQuery);
		out.print(DBUtility.printImagesAsTable(rs));
	%>

</body>
</html>