package controller;

import java.io.File;
import java.io.IOException;
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
import org.hibernate.Session;

import db.DBConstants;
import db.HibernateConnection;
import entities.Image;
import entities.User;

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
		
		Session session = HibernateConnection.getSession();
		
		Integer userId = (Integer) request.getSession().getAttribute("user");
		String category = (String) request.getParameter("category");
		System.out.println("CATEGORY: " + category); // TODO was a test, can't fix this
		
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
						
//						String hql = "FROM User U WHERE U.id = '" + userId + "'";
//						Query<User> query = session.createQuery(hql, User.class);
//						List<User> users = query.getResultList();
//						
//						User theUser = users.get(0); // TODO ... this seems dangerous
						
						session.beginTransaction();
						
						User theUser = session.get(User.class, userId);
//						User dummy = session.load(User.class, userId); // entityManager.getReference or session.load should do the trick too
						
						Image image = new Image();
						image.setUser(theUser);
						image.setReference(fullpath);
						image.setCategory(category);
						image.setDate(sqlformat.format(date));
						image.setFilename(filename);
						
//						System.out.println("Ready to INSERT " + image.toString());
						
						String result = (String) session.save(image);
						System.out.println("Result of image insertion: " + result); // TODO testing
						
						session.getTransaction().commit();
					}
				}
				
				// File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				request.setAttribute("message", "File Upload Failed due to " + e.getMessage());
			}
			
		} else {
			request.setAttribute("message", "Sorry this Servlet is only for files");
		}
		
		response.sendRedirect("home.jsp");
	}
	
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		// Connect and get parameters
//		Connection con = DBConnection.getDBInstance();
//		DBUtility.useDB(con, "gallery");
//		String query;
//		String user = (String) request.getSession().getAttribute("user");
//		String category = (String) request.getParameter("category");
//		System.out.println("CATEGORY: " + category);
//		
//		// Process if Multipart Content
//		if (ServletFileUpload.isMultipartContent(request)) {
//			try {
//				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
//				
//				for (FileItem item : multiparts) {
//					if (!item.isFormField()) {
//						Date date = new Date();
//						SimpleDateFormat sqlformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						SimpleDateFormat fileformat = new SimpleDateFormat("yyyyMMddHHmmss");
//						
//						String name = new File(item.getName()).getName();
//						String filename = fileformat.format(date) + name;
//						String fullpath = UPLOAD_DIRECTORY + File.separator + filename;
//						// Create file as image + date for uniqueness
//						item.write(new File(fullpath));
//						
//						// Save reference as image + date for uniqueness
//						query = "INSERT INTO image (user, reference, filename, category, date) VALUES ('" + user
//								+ "', '" + fullpath + "', '" + filename + "', '" + category + "', '"
//								+ sqlformat.format(date) + "');";
//						DBUtility.executeUpdate(con, query);
//					}
//				}
//				
//				// File uploaded successfully
//				request.setAttribute("message", "File Uploaded Successfully");
//			} catch (Exception e) {
//				request.setAttribute("message", "File Upload Failed due to " + e.getMessage());
//			}
//			
//		} else {
//			request.setAttribute("message", "Sorry this Servlet is only for files");
//		}
//		
//		response.sendRedirect("home.jsp");
//	}
}
