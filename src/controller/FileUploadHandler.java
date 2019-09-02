package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
		
		PrintWriter out = response.getWriter();
		
		Connection con = DBConnection.getDBInstance();
		DBUtility.useDB(con, "gallery");
		String query, selectQuery;
		selectQuery = "SELECT * FROM image;";
		ResultSet rs;
		rs = DBUtility.executeQuery(con, selectQuery);
		out.print(DBUtility.printEntireRSAsTable(rs));
		
		// process only if its multipart content
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
						query = "INSERT INTO image (reference, category, user_id, filename) VALUES ('"
								+ UPLOAD_DIRECTORY + File.separator + name + "', '" + name + "', '1', '" + name + "');";
						DBUtility.executeUpdate(con, query);
					}
				}
				
				// File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
				System.out.println("File(s) created at " + UPLOAD_DIRECTORY);
				// TODO remove
				System.out.println("TEST: " + request.getServletContext().getRealPath("img"));
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}
			
		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}
		
		// request.getRequestDispatcher("/result.jsp").forward(request, response);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}
