package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public Register() {
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
	
	// TODO implement registration of new users
	
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
		// TODO the actual registration
		
		// Now redirect
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}
