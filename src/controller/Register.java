package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnection;
import db.DBUtility;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 *      This should never be accessed (only POST)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("How did you get here?");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO the actual registration
//		
//		// Now redirect
//		request.getRequestDispatcher("home.jsp").forward(request, response);
//		//Find existing entries
//		//Create new user if it doesn't have an entry
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Connect and get parameters
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Connection con = DBConnection.getDBInstance();
		DBUtility.useDB(con, "gallery");
		String query;
		
		// Find existing entries
		query = "SELECT * FROM user WHERE email LIKE '" + email + "';";
		ResultSet rs = DBUtility.executeQuery(con, query);
		
		// Create new user if it doesn't have an entry
		try {
			if (!rs.next()) {
				query = "INSERT INTO user (name, email, password) VALUES ('" + name + "','" + email + "', MD5('"
						+ password + "'));";
				DBUtility.executeUpdate(con, query);
				response.sendRedirect("index.jsp");
			} else {
				PrintWriter out = response.getWriter();
				out.append("<p class='error' style='color:red;'>Registration Failed. User Already Exists.</p>");
				request.getRequestDispatcher("registration.jsp").include(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
