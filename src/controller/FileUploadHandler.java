package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import db.DBConnection;
import db.DBConstants;
import db.DBUtility;

/**
 * Servlet implementation class FileUploadHandler
 */
@WebServlet("/FileUploadHandler")
public class FileUploadHandler extends HttpServlet {
	private final String UPLOAD_DIRECTORY = DBConstants.uploadDirectory;
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadHandler() {
		super();
		File uploadFolder = new File(UPLOAD_DIRECTORY);
		if (!uploadFolder.exists())
			uploadFolder.mkdir();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Connect and get parameters
		Connection con = DBConnection.getDBInstance();
		DBUtility.useDB(con, "gallery");
		String query;
		String user = (String) request.getSession().getAttribute("user");
		String category = (String) request.getParameter("category");
		System.out.println("CATEGORY: " + category);
		
		// Process if Multipart Content
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						Date date = new Date();
						SimpleDateFormat sqlformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat fileformat = new SimpleDateFormat("yyyyMMddHHmmss");
						
						String name = new File(item.getName()).getName();
						String filename = fileformat.format(date) + name;
						String fullpath = UPLOAD_DIRECTORY + File.separator + filename;
						// Create file as image + date for uniqueness
						item.write(new File(fullpath));
						
						// Save reference as image + date for uniqueness
						query = "INSERT INTO image (user, reference, filename, category, date) VALUES ('" + user
								+ "', '" + fullpath + "', '" + filename + "', '" + category + "', '"
								+ sqlformat.format(date) + "');";
						DBUtility.executeUpdate(con, query);
					}
				}
				
				// File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception e) {
				request.setAttribute("message", "File Upload Failed due to " + e.getMessage());
			}
			
		} else {
			request.setAttribute("message", "Sorry this Servlet is only for files");
		}
		
		response.sendRedirect("home.jsp");
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		PrintWriter out = response.getWriter();
//		
//		Connection con = DBConnection.getDBInstance();
//		DBUtility.useDB(con, "gallery");
//		String query, selectQuery;
//		selectQuery = "SELECT * FROM image;";
//		ResultSet rs;
//		rs = DBUtility.executeQuery(con, selectQuery);
//		out.print(DBUtility.printEntireRSAsTable(rs));
//		
//		// Process if Multipart Content
//		if (ServletFileUpload.isMultipartContent(request)) {
//			try {
//				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
//				
//				for (FileItem item : multiparts) {
//					if (!item.isFormField()) {
//						String name = new File(item.getName()).getName();
//						item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
//						// + UPLOAD_DIRECTORY + File.separator + name + "', '" + name + "', '1', '" + name + "');";
//						// Save reference as image + date for uniqueness
//						DBUtility.executeUpdate(con, selectQuery); // was just query instead of selectQuery
//					}
//				}
//				
//				// File uploaded successfully
//				request.setAttribute("message", "File Uploaded Successfully");
//			} catch (Exception e) {
//				System.out.println("TEST: " + request.getServletContext().getRealPath("img"));
//			}
//			
//		} else {
//			request.setAttribute("message", "Sorry this Servlet is only for files");
//		}
//		
//		// request.getRequestDispatcher("/result.jsp").forward(request, response);
//		// request.getRequestDispatcher("/home.jsp").forward(request, response);
//		response.sendRedirect(request.getContextPath() + "/home.jsp");
//	}
}
