package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//Connect and get parameters
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// Check if email and password combo is correct
		
		//Find existing entries
		boolean loginSucceeded = email.equals("test");
		
		if (loginSucceeded) {
			request.setAttribute("loggedIn", Boolean.valueOf(true));
//			request.getRequestDispatcher("home.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/home.jsp");
		} else {
			PrintWriter writer = response.getWriter();
			writer.append("<p class='error'>Login Failed</p>");
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}
}
