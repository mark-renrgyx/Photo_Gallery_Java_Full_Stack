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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public Login() {
//        super();
//    }
//
//	/**
//	 * @see Servlet#init(ServletConfig)
//	 */
//	public void init(ServletConfig config) throws ServletException {
//	}
//
//	/**
//	 * @see Servlet#destroy()
//	 */
//	public void destroy() {
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      This should never be accessed (only POST)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("How did you get here?");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Connection con = DBConnection.getDBInstance();
		DBUtility.useDB(con, "gallery");
		String query;

		query = "SELECT * FROM user WHERE email LIKE '" + email + "' AND password LIKE MD5('" + password + "');";
		ResultSet rs = DBUtility.executeQuery(con, query);
		// TODO: Actual login

		try {
			if (rs.next()) {
				request.getSession().setAttribute("loggedIn", Boolean.valueOf(true));
				request.getSession().setAttribute("user", rs.getString(1));
				request.getRequestDispatcher("home.jsp").forward(request, response);
			} else {
				PrintWriter writer = response.getWriter();
				writer.append("<p class='error'>Login Failed</p>");
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}